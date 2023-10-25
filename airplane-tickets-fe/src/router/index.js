import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Reservations from '../views/MyReservations.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/my-reservations',
        name: 'MyReservations',
        component: Reservations
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;