document.addEventListener('DOMContentLoaded', async () => {

  const command = document.getElementById("command").value
  if (command === "ADD") {
    console.log(command);
    return;
  }

  const response = await fetch('../api/uploadKey?type=photo');
  const data = await response.json();

  const options = {
    cloudName: data.cloudname,
    apiKey: data.apikey,
    uploadSignatureTimestamp: data.timestamp,
    uploadSignature: data.signature,
    cropping: false,
    folder: data.folder
  }


  const processResults = (error, result) => {
    if (!error && result && result.event === 'success') {
      let photoInfo = [];
      photoInfo.push("command" + "=" + 'Add');
      photoInfo.push("url" + "=" + result.info.url);
      photoInfo.push("publicId" + "=" + result.info.public_id);
      photoInfo.push("isMain" + "=" + "false");
      photoInfo.push("productId" + "=" + document.getElementById("productId").value);
      photoInfo = photoInfo.join("&");
      fetch("photo", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: photoInfo
      });
    }
  }

  const myWidget = window.cloudinary.createUploadWidget(
    options,
    processResults
  )
  document
    .getElementById('uploadPhoto')
    .addEventListener('click', () => {
      myWidget.open();
      console.log("opened")
    }, false)
})
