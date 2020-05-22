import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import { routes } from './routes';
import VeeValidate from 'vee-validate';
import mensagem from './pt_BR';
import 'bootstrap/dist/css/bootstrap.css';
import './css/logout.css';
import './css/estilos.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faUserSecret } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

library.add(faUserSecret)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false


Vue.use(VueRouter);
const router = new VueRouter({
  routes: routes,
  mode: 'history'
});

Vue.use(VueResource);

Vue.use(VeeValidate, {
  locale: 'pt_BR',
  dictionary: {
    pt_BR: {
      messages: mensagem
    }
  }
});

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
