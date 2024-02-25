const url = "http://localhost:8080/estoque/product/2"


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


    for (let product in products) {
       tab += `
            <tr>
                <td scope="row">${product.id}</td>
                <td scope="row">${product.name}</td>
                <td scope="row">${product.reference}</td>
                <td scope="row">${product.amount}</td>
            </tr>
            `
    }

    document.getElementById("products").innerHTML = tab
}

async function getAPI(url){
    const response = await fetch(url, { method: "GET" })

    var data = await response.json()
    if(response) hideLoader()
    show(data)
}

getAPI(url)

