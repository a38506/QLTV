async function getAllBook(){
    const response = await fetch('http://localhost:5732/api/book');
    const data = await response.json();
    
    if (Array.isArray(data)) {
        data.map(element => console.log(`Post ${element.id}:`, element));
    }
    document.getElementById("bookDataContent").innerHTML = data.map(element =>
    `<tr>
        <th>${element.Id}</th>
        <th>${element.Name}</th>
        <th>${element.Author}</th>
        <th>${element.Major}</th>
        <th>${element.Quantity}</th>
    </tr>` ).join('');  
}


async function getAllStudent(){
    const response = await fetch('http://localhost:5732/api/student');
    const data = await response.json();
    
    if (Array.isArray(data)) {
        data.map(element => console.log(`Post ${element.id}:`, element));
    }
    document.getElementById("studentDataContent").innerHTML = data.map(element =>
    `<tr>
        <th>${element.Id}</th>
        <th>${element.Name}</th>
        <th>${element.Major}</th>
        <th>${element.Clan}</th>
    </tr>` ).join('');
}

async function getAllTransaction(){
    const response = await fetch('http://localhost:5732/api/transaction');
    const data = await response.json();
    
    if (Array.isArray(data)) {
        data.map(element => console.log(`Post ${element.id}:`, element));
    }
    document.getElementById("transactionDataContent").innerHTML = data.map(element =>
    `<tr>
    
        <th>${element.Id}</th>
        <th>${element.studentId}</th>
        <th>${element.studentName}</th>
        <th>${element.bookName}</th>
        <th>${element.start}</th>
        <th>${element.end}</th>
    </tr>` ).join('');
}

async function submitForm() {
    const IDBook = document.getElementById('IDBook').value;
    const NameBook = document.getElementById('NameBook').value;
    const author = document.getElementById('author').value;
    const major = document.getElementById('major').value;
    const quantity = document.getElementById('quantity').value;

    const formData = {
        IDBook: IDBook,
        NameBook: NameBook,
        author: author,
        major: major,
        quantity: quantity
    };

    try {
        const response = await fetch('http://localhost:5732/api/book/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        (response.ok) 
        console.log('Thêm sách thành công');
        


    } catch (error) {
        console.error('Lỗi:', error);
    }
}