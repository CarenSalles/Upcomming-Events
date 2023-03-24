import axios from "axios";

export default class eventService{

    baseUrl

    constructor(){
        this.baseUrl = 'http://localhost:8080/api';
    }

    async getAllEVents(){    
        axios.defaults.withCredentials = 'include'; 
        const response = axios.get(this.baseUrl + '/events',{

        });

        const getBody = (await response);

        console.log(getBody.data);

        return getBody;
    }
    async getOneEvent(idEvent){
        axios.defaults.withCredentials = 'include'; 
        const response = axios.get(this.baseUrl + `/events/${idEvent}`,{

        });

        const getBody = (await response);


        return getBody;
    }
    async createEvent (event){
        axios.defaults.withCredentials = 'include'; 
        const resp = axios.post(this.baseUrl + '/events',event,{
        });

        const getEvent = (await resp);
        return getEvent;
    }

    async editEvent (event,idEvent){
        axios.defaults.withCredentials = 'include'; 
        const resp = axios.put(this.baseUrl + `/events/${idEvent}`,event,{
        });
        const ediEvent = (await resp);
        return ediEvent;
    }
}