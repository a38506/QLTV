
 
callData()

async function callData() {
  const response = await fetch('https://jsonplaceholder.typicode.com/posts')
  const data = await response.json()
  if (data instanceof Array) data.map(element => console.log(`Post ${element.id}`, element))
}
