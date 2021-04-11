[Java Doc](api/com/company/package-summary.html)
##Name: Nachman Goldman  
##This program reads commands from the console to perform various URL checks.  

Command list:
Examples of the commands structure -> the expected result
Checking the content type of the http headers
t https://cs.hac.ac.il text/html -> true
t https://cs.hac.ac.il text -> true
t https://cs.hac.ac.il img -> false
t https://cs.hac.ac.il/wp-content/uploads/2020/07/hat.png img/gif -> false
t https://cs.hac.ac.il/wp-content/uploads/2020/07/hat.png imag -> true
Search for words from a file in the URL content
w https://en.wikipedia.org/wiki/Main_Page words.txt -> true
w https://www.mechon-mamre.org/i/t/t2601.htm words.txt -> false
Check if the page contains a URL tag
i https://www.mechon-mamre.org/i/t/t2601.htm -> false
i https://en.wikipedia.org/wiki/Help:Introduction -> true
Check if the page is written in English implemented only for English
l https://en.wikipedia.org/wiki/Main_Page english -> true
l https://en.wikipedia.org/wiki/Main_Page engl -> false
l https://www.mechon-mamre.org/i/t/t2601.htm english -> false
'q' to exit
