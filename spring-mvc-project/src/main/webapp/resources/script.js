//var checked = document.getElementById("flexSwitchCheckDefault")

//console.log(checked)

//if(checked.value == "true") {
  //  console.log("Hola")
//} else console.log("NO")

fetch('http://localhost:8080/spring-mvc-project/users/28')
  .then(response => response.json())
  .then(data => console.log(data));