const addImages = (images)=>{
  const container = document.querySelector(".container");
  console.log(images);
  images.forEach(image=>{
    const div = document.createElement("div");
    const img = document.createElement("img");
    const p = document.createElement("p");

    const node = document.createTextNode(image.name);
    p.appendChild(node);
    img.src = `https://robohash.org/${image.name}`;
    div.appendChild(img);
    div.appendChild(p);

    container.innerHTML+=div.outerHTML;
  });



}


fetch("http://localhost:8080/art").then(response=>response.json()).then(images=>addImages(images));
