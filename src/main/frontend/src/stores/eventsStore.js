import { defineStore } from "pinia";
import Repository from "../api/Repository";

//generar un store que guarde los eventos destacados
export const eventsStore = defineStore("featuresEvents", {
  state: () => ({
    fevents: [],
    events: [],
    oneEvent:[]
  }),

  actions: {
    async savefEvents(event) {
      const api = new Repository("events");

      const eventService = api.chooseApi();

      const response = await eventService.createEvent(event);

      console.log(response.status);
    },

    async editEvents(event, idEvent) {
      const api = new Repository("events");

      const eventService = api.chooseApi();

      const response = await eventService.editEvent(event, idEvent);

      console.log(response.status);
    },

    async getOneEvent(id){
      const api = new Repository("events");

      const eventService = api.chooseApi();

      const response = await eventService.getOneEvent(id);

      console.log(response.status);

      this.oneEvent = response.data
    }

    
  },
});
