<script setup>
import { onBeforeMount } from "vue";
import FeatureEvents from "../components/FeaturedEvents.vue"
import CardsComponent from "../components/CardsComponent.vue";
import { eventsStore } from "../stores/eventsStore";
import { eventUserStore } from "../stores/eventUserStore";
import {useRouter} from 'vue-router'


const eventuserstore = eventUserStore();
const eventstore = eventsStore();
const router = useRouter();

onBeforeMount(async() => {

    await eventuserstore.getAll();

})

const addHighlighte =(id) =>{
    eventuserstore.addToHeighlightedEvents(id);
}

const shotDetails=(id) =>{
    router.push({name: 'detailsAdmin', params:{id:id}, props:{id:id}})
}

</script>

<template>
    <main>

        <section>
            <h1>Highlighte-events</h1>
            <div class="wrapper-highlighte-events">
                <div class="container-highlighted-card" v-for="event of eventuserstore.heighlightedEvents">
                <FeatureEvents :event="event" />
                </div>
            </div>
        </section>

        <section class="all-events">
            <div class="container-cards" v-for="event of eventuserstore.events" >
            <CardsComponent class="card"
            :event="event"
            @add-event="addHighlighte(event.id)"
            @click="shotDetails(event.id)"/>
            </div>

        </section>
    </main>
</template>

<style lang="scss" scoped>

main{
    width: 100%;
    height: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;

        .wrapper-highlighted-events{
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            .container-highlighted-card{
                width: 100%;
            }
        }
    }

    .all-events{
        width: 90%;
        height: auto;
        .container-cards{
            margin-top: 2vh;
        }
    }


@media (min-width: 1024px) {
    main{
        flex-direction: row;
        align-items: flex-start;
        justify-content: space-around;
        margin-bottom: 2vh;

            .all-events{
              width: 50%;
              display: flex;
              flex-direction: row;
              flex-wrap: wrap;
              justify-content: space-around;
              align-items: center;

            }
            .card{
                width: 22vw;
            }
        }
    }

</style>
    





