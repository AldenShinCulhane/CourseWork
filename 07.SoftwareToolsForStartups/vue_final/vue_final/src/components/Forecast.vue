<script>
const API_CALL = `http://api.openweathermap.org/data/2.5/weather?q=London,ON,CA&appid=2453333f72b01226743096c5a345d253`
export default {
  created() {
    // fetch on init
    this.fetchData()
  },
  methods: {
    async fetchData() {
      this.weather = await (await fetch(API_CALL)).json()
      this.temp = Math.round((this.weather.main.temp - 273.15) * 100)/100.0
      this.condition = this.weather[0].description
      this.myTS = this.weather.dt
      this.$forceUpdate()
      console.log(this.temp)
    }
  }
}

</script>

<template>
  <div id="w-header">Prediction's timestamp: {{ myTS }}</div>
  <div id="w-condition">Condition: {{ condition }}</div>
  <div id="w-temperature">Temperature: {{ temp }}</div>  
</template>
