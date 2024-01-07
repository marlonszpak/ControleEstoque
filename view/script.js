const url = "http://localhost:8080/estoque/produto/1"

function hideLoader(){
    document.getElementById("loading").style.display = "none"
}

function show(produtos){
    let tab =  `<thead>
                    <th scope="column">ID</th>
                    <th scope="column">Nome</th>
                    <th scope="column">Endereço</th>
                    <th scope="column">Quantidade</th>
                </thead>`


    for (let produto of produtos) {
       tab += `
            <tr>
                <td scope="row">${produto.id}</td>
                <td>${produto.nome}</td>
                <td>${produto.endereco}</td>
                <td>${produto.quantidade}</td>
            </tr>
            `
    }

    document.getElementById("produtos").innerHTML = tab
}

async function getAPI(url){
    const response = await  fetch(url, { method: 'GET' })

    var data = await response.json()
    console.log(data)
    if(response) hideLoader()
    show(data)
}

getAPI(url)