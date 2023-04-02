<template>
  <div>
    <b-button variant="success" @click="getJadwal()">
      Refresh Jadwal
    </b-button>
    <p>{{ title }}</p>
    <div :key="name" v-for="name of Object.keys(jadwal)">
      {{ name }} - {{ jadwal[name] }}
    </div>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      title: "Jadwal Sholat",
      jadwal: {}
    }
  },
  methods: {
    async getJadwal () {
      try {
        const response = await this.$axios.get('https://api.myquran.com/v1/sholat/jadwal/1504/2023/03/31')
        this.jadwal = response.data.data.jadwal
      } catch (error) {
        alert(error)
      }
    }
  }
}
</script>
