In the development onf this assignment we had to change much of our design. In order to have the capabilities/structure
of popular photo editors like Gimp or Photoshop, we designed the model so it starts at the top with a Project model.
    1. The project class holds a list of Layers
        a.  A Layer class now replaces the prior Picture class that we had in the last assignment.
            -> A layer class has the same functionality as the Picture class and more in that it holds a list of 
            array pixels, a hashmap of pixels, a name, width and height, but now we also intrude currentLayer
            and Visible parameters that will be used by the Project class when displaying and applying changes.
            (If we want to apply a filter for example, we need to know which current layer we are on to change 
            the layer.) 
            -> A Layer extends an abstract Export Class which holds different ways to export a Layer based on the 
                file type requested, such as PNG, JPEG or PPM. 
                --> With this abstract class, if it comes to it, we can have a new type of class in the future also 
                    extend it, and we will be able to export that class as well. 
            With these changes it is easier for us to not only export Layers as needed, but its easier for our project
            keep track of who the current layer is, if a certain layer is visible, who to apply a filter to, etc. 
        b. A project also has a name, which can be changed with the changeName method. This name is also exported
            in the txt file in the exportAll function as the first line of the code. This will allow you retrieve
            every piece of information of your project when you reload the txt file holding all of the information. 
            -> In the save function we export the topmost layer in the project 
            -> In the saveall, we save all the layers and note their name, their currrentLayer status, their visibility 
                status, and where they were exported to. 
            -> The loadFile function is to be used by the scanner which will read each line of the file, and feed the 
                necessary/inputted information into this function which will add a layer to 
                the Project with those specifications.
            --> New functionality to add a Layer to the current stack and make that new Layer the current Layer. 
            --> You can also apply a filter to the current layer using applyFilter which will take in one of the file 
                types provided in the FilterModel.   
                --> Our filter method now takes in a Layer and retrieves all the necessary information there and changes  
                    the pictures accordingly
   2. Our controller main will use ProjectController class which should make it much easier to evaulate each line by
        taking each line in a script or input and detecting which programs to run and where.
       SUPPORTED PROGRAMS:  
            FILTER - Will filter the current Layer depending on the inputted string. 
            CURRENT - This will take in a layer name that you want to be current and toggles off the present current lyr 
            VISIBLE - This will toggle the visibility of the layer name inputted.
            SAVE - EXPORTS the topmost layer in a specific file format.
            SAVEALL - EXPORTS all of the layers and records file placement and other necessary of the project/layers.
            LOAD - loads a line in the inputted text file. It will load the file in the project.
            CREATE - This will create a new fresh layer with the inputted width and height given.
    3. The final major changes have to do with ImageUtil. Image Util can now get the file type just from the path of 
      the string inputted and it can read the file from there.
        
    