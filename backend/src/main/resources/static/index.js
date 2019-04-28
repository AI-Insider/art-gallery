const addImages = (images)=>{
  const container = document.querySelector(".container");
  images.forEach(image=>{
    const div = document.createElement("div");
    const img = document.createElement("img");
    const p = document.createElement("p");

    const node = document.createTextNode(image.name);
    p.appendChild(node);
    img.src = `http://localhost:8080/images/?filename=${image.filename}`;
    div.appendChild(img);
    div.appendChild(p);

    container.innerHTML+=div.outerHTML;
  });



}


fetch("http://localhost:8080/art").then(response=>response.json()).then(images=>addImages(images));
