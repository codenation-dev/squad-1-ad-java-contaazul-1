<template>
  <div>
    <div class="justify-content-between row">
      <div>
        <label>Bem vindo(a) {{usuario.nome}}.</label>
        <label>
          Seu token é
          <small>{{usuario.token}}</small>
        </label>
      </div>
      <button @click="logout">
        <i class="fas fa-sign-out-alt"></i>
      </button>
    </div>
    <div class="justify-content-around mt-4 row">
      <select v-model="ambienteSelecionado" class="col-2">
        <option value>Buscar por</option>
        <option v-for="ambiente in ambientes" :value="ambiente.tipo">{{ ambiente.descricao }}</option>
      </select>
      <select v-model="ordenacaoSelecionada" class="col-2">
        <option value disabled>Ordenar Por</option>
        <option v-for="ordem in ordenacao" :value="ordem.tipo">{{ ordem.descricao}}</option>
      </select>
      <select v-model="buscaSelecionado" class="col-2">
        <option value disabled>Buscar Por</option>
        <option v-for="buscar in filtroBuscar" :value="buscar.busca">{{ buscar.descricao }}</option>
      </select>
      <input
        type="search"
        @input="filtro = $event.target.value"
        placeholder="Buscar por"
        class="col-5"
      />
    </div>
    <div>
      <button class="btn btn-success">Arquivar</button>
      <button class="btn btn-danger">Apagar</button>
    </div>
    <div>
      <table>
        <thead>
          <tr>
            <th></th>
            <th>Nivel</th>
            <th>Log</th>
            <th>Eventos</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="erro of descricaoErroFiltro"
            :key="erro.id"
            v-if="erro.ambiente == ambienteSelecionado || filtrarTodosAmbientes"
          >
            <td>
              <input type="checkbox" />
            </td>
            <td>{{erro.nivel}}</td>

            <td>
              {{erro.titulo}}
              <br />
              {{erro.detalhes}}
              <br />
              {{erro.origem}}
            </td>

            <td>{{erro.eventos}}</td>
          </tr>
          <tr></tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Erro from "../../services/erros";
import Usuario from "../../services/usuarios";

export default {
  data() {
    return {
      usuario: {
        nome: "",
        token: ""
      },
      erros: [],
      eventosTeste: [],
      ambienteSelecionado: "",
      ordenacaoSelecionada: "",
      buscaSelecionado: "",
      filtro: "",
      ambientes: [
        { tipo: "homologacao", descricao: "Homologação" },
        { tipo: "desenvolvimento", descricao: "Desenvolvimento" },
        { tipo: "producao", descricao: "Produção" }
      ],
      ordenacao: [
        { tipo: "nivel", descricao: "Nível" },
        { tipo: "frequencia", descricao: "Frequência" }
      ],
      filtroBuscar: [
        { busca: "nivel", descricao: "Nível" },
        { busca: "descricao", descricao: "Descrição" },
        { busca: "origem", descricao: "Origem" }
      ]
    };
  },
  methods: {
    logout() {
      this.$router.push({
        name: "login"
      });
    }
  },
  mounted() {

    this.usuario.nome = this.$route.params.nome;
    this.usuario.token = this.$route.params.token;

    Erro.listar(this.usuario.token).then(resErro => {
      let errors = resErro.data;
      let promises = [];

      errors.forEach(erro => {
        promises.push(
          Erro.eventos(erro.titulo, this.usuario.token).then(resposta => {
            erro.eventos = resposta.data;
          })
        );
      });

      Promise.all(promises).then(() => {
        this.erros = errors;
      });
    });


  },

  computed: {
    filtrarTodosAmbientes() {
      return (
        this.ambienteSelecionado != "homologacao" &&
        this.ambienteSelecionado != "desenvolvimento" &&
        this.ambienteSelecionado != "producao"
      );
    },
    listaOrdenada() {
      if (this.ordenacaoSelecionada == "nivel") {
        return this.erros.sort(function(a, b) {
          if (a.nivel > b.nivel) {
            return 1;
          }
          if (a.nivel < b.nivel) {
            return -1;
          }
          return 0;
        });
      } else if (this.ordenacaoSelecionada == "frequencia") {
        return this.erros;
      } else {
        return this.erros;
      }
    },
    descricaoErroFiltro() {
      if (this.filtro) {
        var exp = new RegExp(this.filtro.trim(), "i");
        if (this.buscaSelecionado == "nivel") {
          return this.listaOrdenada.filter(erro => exp.test(erro.nivel));
        }
        if (this.buscaSelecionado == "descricao") {
          return this.listaOrdenada.filter(erro => exp.test(erro.detalhes));
        }
        if (this.buscaSelecionado == "origem") {
          return this.listaOrdenada.filter(erro => exp.test(erro.origem));
        }
      } else {
        return this.listaOrdenada;
      }
    }
  }
};
</script>


<style scoped>
table {
  margin-top: 10px;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
td,
th {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}
tr:nth-child(even) {
  background-color: #dddddd;
}
table,
th {
  border: 1px solid black;
}
button {
  border-radius: 4px;
  font-size: 15px;
  padding: 10px 20px;
  text-align: center;
  color: white;
  margin-top: 10px;
}
select {
  font-size: 15px;
  padding: 8px 15px;
  text-align: center;
  color: black;
}
input {
  font-size: 15px;
  padding: 8px 15px;
  text-align: left;
  color: black;
  width: 65%;
}
label {
  font-size: 20px;
  margin-top: 10px;
}
li {
  list-style: none;
}
</style>