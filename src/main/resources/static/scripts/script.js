document.addEventListener('DOMContentLoaded', function () {
  var greeting = document.querySelector('.greeting');
  greeting.style.animation = 'textAnimation 4s 1 alternate';

  var container = document.querySelector('.container');
  container.style.animation = 'backgroundAnimation 8s 1';
});

