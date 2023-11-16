callData()

async function callData() {
  const response = await fetch('https://jsonplaceholder.typicode.com/todos/2')
  const json = await response.json()
  console.log(json)
}

      // .then(response => response.json())
      // .then(json => console.log(json))

// fetch('http://localhost:5029/api/settings')
//       .then(response => response.json())
//       .then(json => console.log(json))

async function postData() {
    var referenceKey = "Table"
    var value = "Value"
    var description = "Description"
    var type = "Table"
    var data = {
        referenceKey: referenceKey,
        value: value,
        description: description,
        type: type
    };
    fetch('http://localhost:5029/api/settings', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      }
    )
}

posts=[]

// Take all posts

// console.log("Posts", posts);
// console.log("Post size:", posts.length);
