<script setup>
import CardsComponent from '../components/CardsComponent.vue';
import DetailsForm from '../components/DetailsForm.vue';
import { computed } from 'vue';
import { useLoginStore } from '../stores/loginStore';
import { eventsStore } from '../stores/eventsStore';
import { onBeforeMount } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute()
const useloginstore = useLoginStore();
const eventsstore = eventsStore();
const id = route.params.id

onBeforeMount(async ()=>{
    eventsstore.getOneEvent(id)
})

const eventTemporal ={
    title: 'hola',
    date_hour: '10-10-2020',
    number_participants: 20,
    description: 'esto es un descripcion'
}

</script>

<template>

<div class="container">
    <CardsComponent
    :event="eventsstore.oneEvent"
    />
    <DetailsForm 
    :view="'edit'"
    />
</div>

</template>



<style lang="scss" scoped>

.container{
    flex-direction: column;
    display: flex;
    align-items: center;
}
@media (min-width: 1024px) {
    .container{
        flex-direction: row;
        justify-content: center;
        margin-block: 4%;
    }
}

</style>