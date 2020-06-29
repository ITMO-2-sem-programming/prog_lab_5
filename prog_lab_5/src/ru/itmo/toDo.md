 _execute_script script_

- [x] Merge lab_2 and lab_1  (take commands code from lab_2, move it to classes, 
add field SYNTAX description)
- [x] Not forget to set the 'symbolsForNullValues' before starting using the program.
- [x] FileManager Создается в внутри CollectionManager.
- [x] Add Methods: 'validateInteger', 'Validate Long', etc;
- [x] Continue testing the methods
- [x] Test reading a file in different variations (roots, exists/not exists)
- [x] Rewrite the getMusicBandFromSTDIN() method
- [x] ~~**!!** Доделать FileManager.save  с использованием метода "newFile()", как в примере, сохраненном в E-packet сайте.~~
- [x] ~~**!!** Просмотреть конструкторы "FileInputStream"~~
- [x] ~~**!!** Просмотреть урок про блок try-catch с освобождением ресурсов. Объединить в File-менеджере два блока try в один, оставить два блока catch после него.~~
- [x] ~~Add fields 'validationParams' and 'syntaxDescription' to class Command. Create methods setDescription and setValidationParams.~~
- [x] ~~Check the exceptions thrown be FileManager~~
- [x] ~~Add validateNull() method. It gets the String s, if it's empty -> it returns 'null', else the same string.~~
- [x] ~~**!!** Просмотреть урок про блок try-catch с освобождением ресурсов. Объединить в File-менеджере два блока try в один, оставить два блока catch после него.~~



 <table style="width:100%">
  <tr>
    <th>MethodName</th>
    <th>Tseted in STDIN</th>
    <th>Tested in FILE</th>
  </tr>
  
  <tr>
    <td>HelpCommand</td>
    <td>r</td>
    <td></td>
  </tr>
 
  <tr>
    <td>InfoCommand</td>
    <td>r</td>
    <td></td>
  </tr>
 
   <tr>
     <td>ShowCommand</td>
     <td>@r</td>
     <td></td>
   </tr> 

  <tr>
    <td>InsertCommand</td>
    <td>@r</td>
    <td></td>
  </tr>
  
  <tr>
    <td>UpdateCommand</td>
    <td>@r</td>
    <td></td>
  </tr>

  <tr>
    <td>RemoveByKeyCommand</td>
    <td>@r</td>
    <td></td>
  </tr>

  <tr>
    <td>ClearCommand</td>
    <td>@r</td>
    <td></td>
  </tr>

  <tr>
    <td>SaveCommand</td>
    <td></td>
    <td></td>
  </tr>

  <tr>
    <td>ExecuteScriptCommand</td>
    <td></td>
    <td></td>
  </tr>
  
  <tr>
    <td>ExitCommand</td>
    <td>@r</td>
    <td></td>
  </tr>

  <tr>
    <td>RemoveGreaterCommand</td>
    <td>@</td>
    <td></td>
  </tr>

  <tr>
    <td>ReplaceIfLowerCommand</td>
    <td>@</td>
    <td></td>
  </tr>

  <tr>
    <td>RemoveLowerKeyCommand</td>
    <td>?@r</td>
    <td></td>
  </tr>

  <tr>
    <td>RemoveAllByGenreCommand</td>
    <td>@r</td>
    <td></td>
  </tr>

  <tr>
    <td>MaxByFrontManCommand</td>
    <td>@r</td>
    <td></td>
  </tr>

  <tr>
    <td>FilterGreaterThanSinglesCountCommand</td>
    <td>@r</td>
    <td></td>
  </tr>


</table> 





















