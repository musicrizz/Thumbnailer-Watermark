# Thumbnailer-Watermark
<div>
  <p style="text-align: center">Simple and efficient Thumbnail & Wartermark generator </p>

  <div>
    <h5>Thumbnailer class</h5>
    <p>spacex.jpg = 1200x799 112.79KB </p>
    <code> 
      Path spacex = Paths.get("test_resources", "spacex.jpg").toAbsolutePath();
    </code></br>
    <code>
      byte[] out = <b>Thumbnailer.createThumb(Files.readAllBytes(spacex)); </b>
    </code></br>
    <code>
      Files.write(spacex.resolve(Paths.get("../output", "spacexThumb.jpg")).normalize(), out, StandardOpenOption.CREATE);
    </code></br>
    <p>result : <br/>spacexThumb.jpg = 360x240 13.33KB </p>
    <img src="Thumbnailer-Watermark/test_resources/output/spacexThumb.jpg" >
  </div>
</div>
<hr/>
<div>
  <h5>Wartermark class</h5>
  <code>
  byte[] out = <b>Watermark.createWatermark(Files.readAllBytes(spacex), "\u00A9www.spacex.com");</b>
  </code></br>
  <code>
  Files.write(spacex.resolve(Paths.get("../output", "spacexWatermark.jpg")).normalize(), out, StandardOpenOption.CREATE);
 </code></br>
 <img src="Thumbnailer-Watermark/test_resources/output/spacexWatermark.jpg" style="width: 26rem;">
</div>
