Apple Image: https://www.google.com/url?sa=i&url=http%3A%2F%2Fclipart-library.com%2Fsmall-cliparts.html&psig=AOvVaw36wMUcTHjKFdp9sD0d-171&ust=1623630199450000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNisifyrk_ECFQAAAAAdAAAAABAD 
 

The Koala picture is from the assignment page/whats already given. 

What we have so far is a Model.PixelModel.Model.PixelModel class which contains a Model.PixelModel.Model.PixelModel.Color class and a Positions class. The Model.PixelModel.Model.PixelModel.Color class 
Is there in order to know the RGB values of each pixel. The Model.PixelModel.Position is so we know what row/column that particular
Model.PixelModel.Model.PixelModel is in. This makes it easier to find as we use a Hashmap later on to look for certain pixels that the Kernel needs 
instead of looping through every single pixel until we find the needed pixel position. 

A picture class holds the list of pixels that are provided by the Model.ImageUtil readPPM method, which now returns a picture.
This also holds a pixelToRow in which each key(integer) represents a row of a picture, in which
case we know that if we are in row 0 and we reach the width of the picture, we now need to go to row 2.

We use this later on in order to easily transplant new colors/pixels after a image mutation like ImageBlur, or what not 
occurs. This also makes it easier so that once we have the kernel we need to operate with. We can easily look at 
which pixels need to used to calculate the RGB instead of iterating through each pixel until we find the necessary
Pixels. (Think of needing to go through 3 rows worth of pixels to find the necessary pixel (width * 3)) --This is 
excessive and unnecessary. 


Our Model.PictureModel.Picture Creator class is used to create a picture in which case one can use the makePicture method and 
just pass in the width, height and list of colors of the pixels. This is used by the other method in 
Model.PictureModel.PictureCreator, checkerBoard, which will produce a picture of a checkerboard given two colors. 
You need to pass in the two colors, the width and height of the image you want to produce, and the tile size in terms of
pixels. This tile size needs to be compatible with the width and height passed in, meaning you can't have 
a tileSize that is 90% of the width as this doesn't make any sense. 