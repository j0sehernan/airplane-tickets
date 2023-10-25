<template>
    <div class="card flex flex-wrap gap-3 p-fluid">
        <span class="p-float-label">
            <AutoComplete v-model="selectedOriginAirport" :suggestions="airports" @complete="searchAirports"
                field="concatenatedField" />
            <label for="ac">Origen</label>
        </span>
        <span class="p-float-label">
            <AutoComplete v-model="selectedDestinationAirport" :suggestions="airports" @complete="searchAirports"
                field="concatenatedField" />
            <label for="ac">Destino</label>
        </span>
        <div class="flex align-items-center">
            <Checkbox v-model="onlyDeparture" :binary="true" inputId="ingredient1" />
            <label for="ingredient1" class="ml-2">Sólo Ida</label>
        </div>
        <span class="p-float-label">
            <Calendar v-model="departureDate" inputId="departure_date" dateFormat="dd/mm/yy" />
            <label for="departure_date">Fecha de Ida</label>
        </span>
        <span class="p-float-label">
            <Calendar v-model="returnDate" inputId="return_date" dateFormat="dd/mm/yy" :disabled="onlyDeparture" />
            <label for="return_date">Fecha de Vuelta</label>
        </span>
        <div class="flex align-items-center">
            <Button label="Buscar" icon="pi pi-search" @click="searchFlights" />
        </div>
    </div>
    <div class="card">
        <DataTable :value="flights" removableSort tableStyle="min-width: 50rem">
            <Column field="departure_airport_full" header="Origen" sortable style="width: 25%"></Column>
            <Column field="arrival_airport_full" header="Destino" sortable style="width: 25%"></Column>
            <Column field="departure_time" header="Fecha y hora de Salida" sortable style="width: 20%"></Column>
            <Column field="arrival_time" header="Fecha y hora de Llegada" sortable style="width: 20%"></Column>
            <Column header="Reservar" style="width: 10%">
                <template #body="slotProps">
                    <Button label="Reservar" @click="bookFlight(slotProps.data)" />
                </template>
            </Column>
        </DataTable>
    </div>

    <Dialog v-model:visible="modalVisible" modal header="Header" :style="{ width: '40vw' }">
        <template #container="slotProps">
            <div class="surface-card border-round shadow-2 p-4">
                <div class="text-900 font-medium mb-2 text-xl">Resumen de la reserva</div>
                <p class="mt-0 mb-4 p-0 line-height-3">Nunc mi ipsum faucibus vitae aliquet nec. Lacus sed viverra tellus in
                    hac
                    habitasse platea dictumst.</p>
                <div class="flex mb-4 flex-column lg:flex-row">
                    <div class="surface-50 p-6 flex-auto">
                        <div class="flex flex-column gap-2">
                            <label for="username">E-mail</label>
                            <InputText id="username" v-model="customerEmail"
                                @blur="getCustomerDetailsByEmail(customerEmail)" aria-describedby="username-help" />
                        </div>
                        <div class="flex flex-column gap-2 pt-10">
                            <label for="username">Nombre completo</label>
                            <InputText id="username" v-model="customerFullName" aria-describedby="username-help" />
                        </div>
                        <div class="flex flex-column gap-2">
                            <label for="username">Celular</label>
                            <InputText id="username" v-model="customerPhone" aria-describedby="username-help" />
                        </div>
                    </div>
                    <div class="surface-50 p-6 flex-auto mx-0 my-3 lg:my-0 lg:mx-3">
                        <div class="text-600 mb-3">Origen</div>
                        <span class="text-blue-600 font-medium text-xl">{{ reservationItineray.departure_airport_full
                        }}</span>
                        <div class="text-600 mb-3">Destino</div>
                        <span class="text-blue-600 font-medium text-xl">{{ reservationItineray.arrival_airport_full
                        }}</span>
                        <div class="text-600 mb-3">Fecha y Hora de Salida</div>
                        <span class="text-blue-600 font-medium text-xl">{{ reservationItineray.departure_time }}</span>
                        <div class="text-600 mb-3">Fecha y Hora de Llegada</div>
                        <span class="text-blue-600 font-medium text-xl">{{ reservationItineray.arrival_time }}</span>
                    </div>
                </div>
                <Button icon="pi pi-check" label="Confirmar Reservación" @click="createReservation"></Button>
            </div>
        </template>
    </Dialog>
</template>
  
<script setup>
import { ref, onMounted, watch } from 'vue';
import axios from 'axios';

const returnDate = ref();
const departureDate = ref();
const onlyDeparture = ref(true);

const airports = ref([]);
const selectedOriginAirport = ref(null);
const selectedDestinationAirport = ref(null);
const flights = ref([]);

const searchAirports = async (event) => {
    let location = event.query
    try {
        const response = await axios.get(`http://localhost:8080/airplane-tickets-ms/api/airports?location=${location}`);
        airports.value = response.data.map(airport => {
            airport.concatenatedField = airport.name + ' (' + airport.location + ' - ' + airport.code + ')';
            return airport;
        });
    } catch (error) {
        console.error('Hubo un error al obtener los datos de los aeropuertos:', error);
    }
};

const searchFlights = async () => {
    const params = {
        departureAirportId: selectedOriginAirport.value?.id,
        arrivalAirportId: selectedDestinationAirport.value?.id,
        departureDate: formatDateAsYYYYMMDD(departureDate.value),
    };

    try {
        const response = await axios.get('http://localhost:8080/airplane-tickets-ms/api/flight-itineraries', { params });
        flights.value = response.data.map(flight_itinerary => {
            flight_itinerary.departure_time = formatDateTime(flight_itinerary.departure_time)
            flight_itinerary.arrival_time = formatDateTime(flight_itinerary.arrival_time)
            flight_itinerary.departure_airport_full = flight_itinerary.departure_airport.name + ' (' + flight_itinerary.departure_airport.location + ' - ' + flight_itinerary.departure_airport.code + ')';
            flight_itinerary.arrival_airport_full = flight_itinerary.arrival_airport.name + ' (' + flight_itinerary.arrival_airport.location + ' - ' + flight_itinerary.arrival_airport.code + ')';
            return flight_itinerary;
        });;
    } catch (error) {
        console.error('Error al buscar itinerarios de vuelo:', error);
    }
};

onMounted(() => {
    searchAirports('');
});

const formatDateAsYYYYMMDD = (date) => {
    if (!date) return null;

    const dateObj = new Date(date);

    const year = dateObj.getFullYear();
    const month = dateObj.getMonth() + 1;
    const day = dateObj.getDate();

    return `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
};

watch(onlyDeparture, (newVal) => {
    if (newVal) {
        returnDate.value = null;
    }
});

function formatDateTime(input) {
    const date = new Date(input);

    const pad = (num) => (num < 10 ? '0' + num : num);

    const day = pad(date.getDate());
    const month = pad(date.getMonth() + 1);
    const year = date.getFullYear();

    const hours = pad(date.getHours());
    const minutes = pad(date.getMinutes());
    const seconds = pad(date.getSeconds());

    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
}

const bookFlight = (flightItinerary) => {
    customerEmail.value = null
    customerFullName.value = null
    customerPhone.value = null
    modalVisible.value = true
    reservationItineray = flightItinerary
};

let modalVisible = ref(false)
let reservationItineray = ref()

const customerEmail = ref();
const customerFullName = ref();
const customerPhone = ref();

const getCustomerDetailsByEmail = async (email) => {
    try {
        const response = await axios.get(`http://localhost:8080/airplane-tickets-ms/api/customers/by-email/${email}`);
        const customerDetails = response.data;
        customerFullName.value = customerDetails.full_name
        customerPhone.value = customerDetails.phone
    } catch (error) {
        console.error('Error al obtener detalles del cliente:', error);
    }
};

const createReservation = async () => {
    try {
        const payload = {
            customer: {
                email: customerEmail.value,
                full_name: customerFullName.value,
                phone: customerPhone.value
            },
            flight_itinerary_id: reservationItineray.id
        };

        await axios.post('http://localhost:8080/airplane-tickets-ms/api/reservations', payload);
        modalVisible.value = false;
        alert("Reserva creada exitosamente!");
    } catch (error) {
        console.error('Error al crear la reserva:', error);
        alert("Hubo un error al crear la reserva. Por favor, inténtalo de nuevo.");
    }
};
</script>