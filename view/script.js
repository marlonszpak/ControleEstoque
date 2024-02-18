const url = "http://localhost:8080/estoque/product/1"

function hideLoader(){
    document.getElementById("loading").style.display = "none"
}

function show(products){
    let tab =  `<thead>
                    <th scope="column">ID</th>
                    <th scope="column">Nome</th>
                    <th scope="column">Endereço</th>
                    <th scope="column">Quantidade</th>
                </thead>`


    for (let product of products) {
       tab += `
            <tr>
                <td scope="row">${product.id}</td>
                <td>${product.name}</td>
                <td>${product.reference}</td>
                <td>${product.amount}</td>
            </tr>
            `
    }

    document.getElementById("products").innerHTML = tab
}

async function getAPI(url){
    const response = await  fetch(url, { method: 'GET' })

    var data = await response.json()
    console.log(data)
    if(response) hideLoader()
    show(data)
}

getAPI(url)