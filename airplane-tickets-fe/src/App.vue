<template>
  <TabMenu v-model:activeIndex="active" :model="items">
    <template #item="{ label, item, props }">
      <router-link v-if="item.route" v-slot="routerProps" :to="item.route" custom>
        <a :href="routerProps.href" v-bind="props.action" @click="($event) => routerProps.navigate($event)"
          @keydown.enter.space="($event) => routerProps.navigate($event)">
          <span v-bind="props.icon" />
          <span v-bind="props.label">{{ label }}</span>
        </a>
      </router-link>
    </template>
  </TabMenu>
  <router-view></router-view>
</template>
<script setup>

import { ref, onMounted, watch } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();

const active = ref(0);
const items = ref([
  {
    label: 'Home',
    icon: 'pi pi-fw pi-home',
    route: '/'
  },
  {
    label: 'Mis Reservaciones',
    icon: 'pi pi-fw pi-calendar',
    route: '/my-reservations'
  }
]);

onMounted(() => {
  active.value = items.value.findIndex((item) => route.path === router.resolve(item.route).path);
})

watch(
  route,
  () => {
    active.value = items.value.findIndex((item) => route.path === router.resolve(item.route).path);
  },
  { immediate: true }
);
</script>