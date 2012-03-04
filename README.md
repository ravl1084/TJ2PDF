TJ2PDF: Create a printable Gantt chart from Taskjuggler
=======================================================

This is an attempt to transfer the wonderful Gantt charts provided by
[Taskjuggler](http://www.taskjuggler.org/) to LaTeX, allowing them to be
printed and included into other LaTeX documents.

TJ2PDF is written in Java and should be fairly portable.

Dependencies
------------
* Taskjuggler 3.x to produce the CSV report that is fed to TJ2PDF.
* Texlive 2011 to compile the resulting LaTeX file. Other versions of
  Texlive might work, but have not been tested.

Usage
-----
1. Include the following at the end of your TJP file:
<pre>
       taskreport printout "Print" {
            formats csv
            columns id, name, duration, start, complete, precursors
       }
</pre>
2. Run TJ3 on your project file to produce the report "Print.csv"
3. Copy TJ2PDF-XXXX.jar to the same directory where the CSV file is.
4. Run `java -jar TJ2PDF-XXXX.jar`. This should produce a file
   "Chart.txt".
5. Open the file "gantt.tex" and place the contents of "Chart.txt" where
   indicated. Run "gantt.tex" through LaTeX.

License
-------
TJ2PDF is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, version 3.

TJ2PDF is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with TJ2PDF.  If not, see <http://www.gnu.org/licenses/>.
