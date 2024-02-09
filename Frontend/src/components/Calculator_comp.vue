<template>
  <div class="calculator">
    <button class="displayScreen" >{{ result }}</button>
    <button class="btn ctrl_btn" @click="addop('%')">%</button>
    <button class="btn ctrl_btn" @click="reset()">CE</button>
    <button class="btn ctrl_btn" @click="reset()">C</button>
    <button class="btn delete" @click="deleteEntry()">DEL</button>
    <button class="btn ctrl_btn" @click="unary('#')">1/x</button>
    <button class="btn ctrl_btn" @click="unary('^')">x <sup>2</sup></button>
    <button class="btn ctrl_btn" @click="unary('$')">x <sup>1/2</sup></button>
    <button class="btn ctrl_btn" @click="addop('/')">/</button>
    <button class="btn" @click="add(7)">7</button>
    <button class="btn" @click="add(8)" >8</button>
    <button class="btn" @click="add(9)">9</button>
    <button class="btn ctrl_btn" @click="addop('x')">x</button>
    <button class="btn" @click="add(4)">4</button>
    <button class="btn" @click="add(5)">5</button>
    <button class="btn" @click="add(6)">6</button>
    <button class="btn ctrl_btn" @click="addop('-')">-</button>
    <button class="btn" @click="add(1)">1</button>
    <button class="btn" @click="add(2)">2</button>
    <button class="btn" @click="add(3)">3</button>
    <button class="btn ctrl_btn" @click="addop('+')">+</button>
    <button class="btn" @click="toggle()">+/-</button>
    <button class="btn" @click="add(0)">0</button>
    <button class="btn" @click="addop('.')">.</button>
    <button class="btn equal" @click="addop('=')">=</button>
  </div>
</template>

<script>
// import { response } from 'express';

export default {
  name: 'Calculator_comp',
  data() {
    return {
      x: 0,
      y: 0,
      operator: '',
      result: "",
      isError: false
    }
  },
  methods: {
    add(x) {
      fetch('http://localhost:8080/add',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(x)
      }).then(response=>{
        return response.text();
      }).then(data=>{
        this.result = data;
      })
      // .catch(err=>{
      //   console.log(err);
      //   this.result = "E";
      //   this.isError = true;
      //   this.handleError(this.isError);
      // })
    },
    addop(operator){
      fetch('http://localhost:8080/operator',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(operator)
      }).then(response=>{
        return response.text();
      }).then(data=>{
        this.result = data;
      })
      // .catch(err=>{
      //   console.log(err);
      //   this.result = "E";
      //   this.isError = true;
      //   this.handleError(this.isError);
      // })
    },
    unary(operator){
      fetch('http://localhost:8080/unary',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(operator)
      }).then(response=>{
        return response.text();
      }).then(data=>{
        this.result = data;
      })
      // .catch(err=>{
      //   console.log(err);
      //   this.result = "E";
      //   this.isError = true;
      //   this.handleError(this.isError);
      // })
    },
    reset(){
      fetch('http://localhost:8080/reset',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        //body: JSON.stringify(x)
      }).then(response=>{
        return response.text();
      }).then(data=>{
        this.result = data;
      }).catch(err=>{
        console.log(err);
        this.result = "E";
        this.isError = true;
        this.handleError(this.isError);
      })
    },
    deleteEntry(){
      fetch('http://localhost:8080/delete',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        //body: JSON.stringify(x)
      }).then(response=>{
        return response.text();
      }).then(data=>{
        this.result = data;
      }).catch(err=>{
        console.log(err);
        this.result = "E";
        this.isError = true;
        this.handleError(this.isError);
      })
    },
    toggle(){
      fetch('http://localhost:8080/toggle',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        //body: JSON.stringify(x)
      }).then(response=>{
        return response.text();
      }).then(data=>{
        this.result = data;
      }).catch(err=>{
        console.log(err);
        this.result = "E";
        this.isError = true;
        this.handleError(this.isError);
      })
    },
    handleError(isError){
      if(isError){
        fetch('http://localhost:8080/error',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.result)
      }).then(response=>{
        return response.text();
      })
      this.result = "E";
      this.isError = !isError;
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.calculator{
  display: grid;
  grid-template-columns: repeat(4, 75px);
  grid-auto-rows: minmax(50px, auto);
  padding-top: 7%;
  text-align: center;
  background-color:white;
  place-content: center;
  /* align-items: center; */
  /* box-shadow: 0 0 10px 0 rgba(0,0,0,0.2); */
}

.displayScreen{
  grid-column: 1 / 5;
  background-color: #aaaaaa;
  font-size: 22px;
  height: 45px;

  text-align: left;
  padding-left: 10px;
  padding-top: 10px;
  padding-bottom: 10px;
  /* padding: 10px, 10px, 10px, 10px; */
  border: 0;
  color: #fff;
  /* border-top-right-radius: 30px;
  border-top-left-radius: 30px; */
  border-radius: 30px;
  margin-bottom: 7px;
}
.btn{
  background-color: white;
  border: 1px solid #dddddd;
  /* color: #333333; */
  font-size: 18px;
  border-radius: 40px;
  padding-top: 10%;
  margin-bottom:5px;
  margin-right: 3px;
  /* font-weight: bold; */
  /* font-weight:400; */
  /* box-shadow: 0 0 5px 0 rgba(0,0,0,0.2); */
  transition: box-shadow 0.3s , opacity 0.3 ;
}
.btn:hover{
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3 );
}
.btn:active{
  opacity: 0.5;
}
.ctrl_btn{
  background-color: #eee;
}
.equal{
  background-color: #ff97a9;
  color: #fff;
}
.delete{
  background-color: #666666;
  color: #fff;
}
</style>
