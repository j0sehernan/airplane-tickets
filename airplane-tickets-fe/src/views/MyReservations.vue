<template>
    <div class="card flex flex-wrap gap-3 p-fluid">
        <span class="p-float-label">
            <InputText v-model="selectedEmail" />
            <label for="ac">E-mail</label>
        </span>
        <div class="flex align-items-center">
            <Button label="Buscar" icon="pi pi-search" @click="searchMyFlights" />
        </div>
    </div>
    <div class="card">
        <DataTable :value="flights" removableSort tableStyle="min-width: 50rem">
            <Column field="departure_airport_full" header="Origen" sortable style="width: 25%"></Column>
            <Column field="arrival_airport_full" header="Destino" sortable style="width: 25%"></Column>
            <Column field="departure_time" header="Fecha y hora de Salida" sortable style="width: 20%"></Column>
            <Column field="arrival_time" header="Fecha y hora de Llegada" sortable style="width: 20%"></Column>
        </DataTable>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import axios from 'axios';

const selectedEmail = ref('');
const flights = ref([]);

const searchMyFlights = async () => {
    try {
        const email = selectedEmail.value;
        const response = await axios.get(`http://localhost:8080/airplane-tickets-ms/api/reservations/by-customer-email/${email}`);

        flights.value = response.data.map(flight => {
            return {
                departure_airport_full: `${flight.flight_itinerary.departure_airport.name} (${flight.flight_itinerary.departure_airport.code}), ${flight.flight_itinerary.departure_airport.location}`,
                arrival_airport_full: `${flight.flight_itinerary.arrival_airport.name} (${flight.flight_itinerary.arrival_airport.code}), ${flight.flight_itinerary.arrival_airport.location}`,
                departure_time: formatDateTime(flight.flight_itinerary.departure_time),
                arrival_time: formatDateTime(flight.flight_itinerary.arrival_time)
            }
        });
    } catch (error) {
        console.error("Hubo un error al obtener las reservas:", error);
    }
};

function formatDateTime(input) {
    const date = new Date(input);

    const pad = (num) => (num < 10 ? '0' + num : num);

    const day = pad(date.getDate());
    const month = pad(date.getMonth() + 1); // Los meses en JavaScript son 0-based
    const year = date.getFullYear();

    const hours = pad(date.getHours());
    const minutes = pad(date.getMinutes());
    const seconds = pad(date.getSeconds());

    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
}
</script>