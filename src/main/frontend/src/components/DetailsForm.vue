<script setup>
import{ref, computed}from"vue";
import { eventsStore } from "../stores/eventsStore.js";
import { useLoginStore } from "../stores/loginStore";

const title = ref("");
const date = ref("");
const capacity = ref("");
const description = ref(" ");
const eventstore = eventsStore();
const useloginstore = useLoginStore();

const props = defineProps(
  {
    id:{
      type:Number,
    },
    view:{
      type: String,
      default: 'create'
    }
  }
)

function send(){
 
 const events = {
   title: title.value,
   date_hour: date.value,
   number_participants: capacity.value,
   description: description.value,
  };
  eventstore.savefEvents(events) 
};
function edit(){
 
 const events = {
   title: title.value,
   date_hour: date.value,
   number_participants: capacity.value,
   description: description.value,
  };
  eventstore.editEvents(events,props.id) 
};

const disable = computed(()=>{
  return (useloginstore.roleLogin=='ROLE_USER') ? true: false;
})
</script>

<template>
  <div class="form-container">
    <div class="list-title">
      <h3>Title</h3>
    </div>

    <input :disabled="disable" class="input-info" v-model="title" placeholder="Enter a title" />

    <div class="list-date-title">
      <h3>Date</h3>
    </div>

    <input :disabled="disable" class="input-info" type="date" v-model="date" />

    <div class="list-maxParticipants-title">
      <h3>Max participants</h3>
    </div>

    <input :disabled="disable" class="input-info" type="number"
      v-model="capacity"
      placeholder="max participants"
    />
    
    <div class="description-title">
      <h3>Description</h3>
    </div>

    <textarea :disabled="disable" class="textarea-info" v-model="description"></textarea>
    <div class="card__buttons mt-4">
          <v-btn v-if="view == 'create' && useloginstore.roleLogin == 'ROLE_ADMIN'" variant="flat" color="success" class="button mr-2" v-on:click="send()">SUBMIT</v-btn>
          <v-btn v-if="view == 'edit' && useloginstore.roleLogin == 'ROLE_ADMIN'" variant="flat" color="warning" class="button mr-2" v-on:click="edit()">EDIT</v-btn>
          <v-btn v-if="view == 'edit' && useloginstore.roleLogin == 'ROLE_ADMIN'" variant="flat" color="red" class="button mr-2" v-on:click="edit()">DELETE</v-btn>
          <v-btn v-if="view == 'edit' && useloginstore.roleLogin == 'ROLE_ADMIN'" variant="flat" color="blue" class="button mr-2" v-on:click="edit()">ADD TO SLIDER</v-btn>
    </div>
  </div>

  
</template>

<style lang="scss" scoped>
.form-container {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  
  .input-info{
    width: 80%;
    height: 5vh;
    background-color: #ece5e5;
    border-radius: 10px;
  }
  .textarea-info{
    width: 80%;
    height: 10vh;
    background-color: #ece5e5;
    border-radius: 10px;
  }
  .card__buttons{
    width: 80%;
    margin: 0 auto;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
  }
  .button{
    width: 30%;
  }
}
@media (min-width: 1024px){
  .form-container{
    width: 50%;
  }
}
</style>
