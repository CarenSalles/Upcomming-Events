import axios from 'axios';
import { defineStore } from 'pinia'
import Repository from '../api/Repository.js';
import apiUsers from '../api/apis/apiUsers.js';
import { watch } from 'vue';

export const useLoginStore = defineStore('login', {

    state: () => ({
        login: {},
        statusLogin: 0,
        roleLogin: '',
        isAuthenticate: false,
        username: ''
    }),

    actions: {
        saveLogin(username, password){
            this.login={username:username, password:password}
            
        },
        async loginSession(username, password){

            const api = new Repository('users');
            const apiUsers = api.chooseApi();

            const response = await apiUsers.acces(username, password);


            this.statusLogin = response.status
            this.roleLogin = response.data.role
            this.username = response.data.username
            
            if(response.status == 202) this.isAuthenticate = true;


            return response;
        },
        async register(){

            const api = new Repository('users');
            const apiUsers = api.chooseApi();

            const response = await apiUsers.register();

            console.log(response.status);

            this.statusLogin = response.status
            this.roleLogin = response.data.role

            return response;
        },
        async logOut(){
            const api = new Repository('users');
            const apiUsers = api.chooseApi();

            const response = await apiUsers.logOut();

        },
        async cleanLoginSession(){
            this.statusLogin = 0
            this.roleLogin = ''
            this.isAuthenticate = false
            this.username = ''
        }
    },
});