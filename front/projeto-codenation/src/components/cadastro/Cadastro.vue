<template>
  <div id="cadastro" class="centralizado">
    <h1>{{ titulo }}</h1>
        <form @submit.prevent="salvar">
            <div class="margem">
                <input type="text" size=50 placeholder="nome" v-model="usuario.nome">
            </div>
            <div class="margem">
                <input type="text" size=50 placeholder="e-mail" v-model="usuario.email">
            </div>
            <div class="margem">
                <input type="password" size=50 placeholder="password" v-model="usuario.senha">
            </div>
            <div class="margem">
                <input type="text" size=50 placeholder="CPF" v-model="usuario.cpf">
            </div>
            <div class="margem">
                <input type="text" size=50 placeholder="token" v-model="usuario.token">
            </div>
            <div class="margem">
                <button>Cadastrar</button>
            </div>
        </form>
  </div>
</template>

<script>

import Usuario from '../../services/usuarios'

export default {
  name: "cadastro",
  data() {
    return {
      titulo: "Cadastrar-se",
      usuario: {
        nome: '',
        email: '',
        senha: '',
        token: '',
        cpf: ''
      }
    }
  },

  methods:{
    salvar(){
      Usuario.salvar(this.usuario).then(resposta => {
        alert('Salvo com sucesso')
      })
    }
  }
}
</script>

<style>
.centralizado {
  text-align: center;
}
.margem {
  margin-top: 10px;
}
.inline {
    
    margin-top: 50px;
    display: inline;
    
}
</style>
