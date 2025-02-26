# In-Class 4: Color Chooser

![Screenshot](screenshot.png)
## Make the Menu
Use a `ExposedDropdownMenu` drop down menu that lets you select the background color. Inside of an `ExposedDropDownMenuBox`, you will give it an `OutLinedTextField` to display the string for the current color and an `ExposedDropDownMenu` for the list of colors.

`ExposedDropDownMenuBox` takes a boolean `expanded` that determines whether the menu is open or 
closed, and a lambda `onExpandedChange` that executes when the menu opens and closes.

`OutlinedTextField` has a `value` for what text is shown, as well as a `label` to describe the 
menu. You can give it a `trailingIcon` that changes based on whether the menu is expanded. You 
can use `ExposedDropdownMenuDefaults.TrailingIcon` for that.

`ExposedDropDownMenu` takes an `expanded` Boolean to signify the menu is open or closed. It also 
takes an `onDismissRequest` lambda, that can simply set your `expanded` to `false`. To show all 
the color strings in a List, you can call `myList.forEach` that you give a lambda for what to do 
for each item. For each color, make a `DropdownMenuItem` with the `text` being the string item and 
`onClick` that sets your `expanded` to false and sets the current color to the current item from 
the list.
