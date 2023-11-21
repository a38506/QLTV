



      // fetch('http://localhost:5029/api/settings', {
      //     method: 'POST',
      //     headers: {
      //         'Content-Type': 'application/json'
      //     },
      //     body: JSON.stringify(data)
      // });
 
callData()

async function callData() {
  const response = await fetch('https://jsonplaceholder.typicode.com/post')
  
  async function postData() {
  for (let i = 1; i <= 100; i++) {
      var referenceKey = "Table" + i;
      var value = "Value" + i;
      var description = "Description" + i;
      var type = "Table" + i;

      var data = {
          referenceKey: referenceKey,
          value: value,
          description: description,
          type: type
      };
  
    // fetch('http://localhost:5029/api/settings', {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json'
    //   },
    //   body: JSON.stringify(postData)
    // });

  const json = await response.json()
  console.log(json)
}

  }
}
postData();
