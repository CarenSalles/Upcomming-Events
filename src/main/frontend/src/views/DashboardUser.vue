<script setup>
import { eventUserStore } from '../stores/eventUserStore';
import { onBeforeMount } from 'vue';
import FeaturedEvents from '../components/FeaturedEvents.vue';
import CardsComponent from '../components/CardsComponent.vue';
import {useLoginStore} from '../stores/loginStore';
import {useRouter} from 'vue-router';
import { ref } from 'vue';

const eventstore = eventUserStore();
const loginstore = useLoginStore();
const router = useRouter();

onBeforeMount(async() => {

   await eventstore.getAll();

})

const addUserToEvent = async (id)=>{


  const json = await eventstore.incrementEvent(id)
  
  if(json.participants > json.actual){

    eventstore.addUserToEvent(loginstore.username, id);
    
  }
  
  
}

const shotDetails=(id) =>{
    router.push({name: 'detailsUser', params:{id:id}, props:{id:id}})
}
</script>

<template>
  <main>

    <section>
      <h1>Highlighted-events</h1>
      <div class="wrapper-highlighted-events">
        <div class="container-highlighted-card" v-for="event of eventstore.heighlightedEvents">
          <FeaturedEvents
          :event="event"
          />
        </div>
      </div>
    </section>

    <section class="all-events">
      <div class="container-cards" v-for="event of eventstore.events">
        <CardsComponent class="card"
        :event="event" 
        @add-event="addUserToEvent(event.id)"
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
            
        }
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
    
</style>