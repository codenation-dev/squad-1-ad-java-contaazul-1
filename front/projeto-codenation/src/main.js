import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import { routes } from './routes';
import VeeValidate from 'vee-validate';
import mensagem from './pt_BR';

Vue.use(VueRouter);
const router = new VueRouter({
  routes: routes
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
