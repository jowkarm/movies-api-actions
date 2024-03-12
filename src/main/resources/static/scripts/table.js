window.onload = function (){
    //select all edit links
    const editLinks = document.querySelectorAll('.edit');
    for(const link of editLinks){
        link.onclick = editRecord;
    }


    //select all edit links
    const deleteLinks = document.querySelectorAll('.delete');
    for(const link of deleteLinks){
        link.onclick = deleteRecord;
    }
}

function editRecord(event){
    // look for id we are editing
    const editLink = event.target;
    const row = editLink.parentElement.parentElement;
    const cells = row.children;

    const id = cells[0].innerHTML;


    const species = cells[2].innerHTML;


    // assign input tag to the cell
    cells[2].innerHTML = `<input type="text" id="species" value="${species}">`;


    const location = cells[4].innerHTML;


    // assign input tag to the cell
    cells[4].innerHTML = `<input type="text" id="location" value="${location}">`;
}

function deleteRecord(event){
    // look for id we are editing
    const editLink = event.target;
    const row = editLink.parentElement.parentElement;
    const cells = row.children;

    const id = cells[0].innerHTML;

    // remove the row
    row.remove();
}
