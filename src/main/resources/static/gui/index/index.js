// This script initializes after the document content has been fully loaded.
document.addEventListener('DOMContentLoaded', function () {
  // Find the span element with class 'handwriting' inside the div with class 'greeting'.
  const handwriting = document.querySelector('.greeting .handwriting');
  const body = document.querySelector('body');

  if (handwriting) {
      // Add the 'handwriting-animation' class to enable text animation.
    handwriting.classList.add('handwriting-animation');
  } else {
    console.log("The handwriting element was not found.");
  }

  if (body) {
      // Add the 'background-animation' class to enable background animation.
    body.classList.add('background-animation');
  } else {
    console.log("The body element was not found.");
  }
});
