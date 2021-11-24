document.addEventListener('DOMContentLoaded', async () => {
  const response = await fetch('../service/uploadSignature?type=photo');
  const data = await response.json();
  console.log(data);

  const options = {
    cloudName: data.cloudname,
    apiKey: data.apikey,
    uploadSignatureTimestamp: data.timestamp,
    uploadSignature: data.signature,
    cropping: false,
    folder: data.folder
  }
  console.log(options);


  const processResults = (error, result) => {
    if (!error && result && result.event === 'success') {
      console.log(result)
    }else{
      console.log(error)
    }
  }

  const myWidget = window.cloudinary.createUploadWidget(
    options,
    processResults
  )
  document
    .getElementById('upload_widget')
    .addEventListener('click', () => myWidget.open(), false)
})
