@Echo Off

Rem Java RE Dir
Rem Set JRE=%ProgramFiles%\Java\jre6
Rem Set JRE=%ProgramFiles%\Java\jre1.8.0_333
Set JRE=%ProgramFiles%\Java\jdk-18.0.1.1

Rem Path
Set PATH=%JRE%\bin;%PATH%

Rem Swing Default Look and Feel (-Dswing.defaultlaf=%LF%)
Set LF=com.sun.java.swing.plaf.windows.WindowsLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.gtk.GTKLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.mac.MacLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.motif.MotifLookAndFeel
Rem Set LF=javax.swing.plaf.nimbus.NimbusLookAndFeel
Rem Set LF=javax.swing.plaf.metal.MetalLookAndFeel

Rem Java Language
Set LANG=
Rem Set LANG=-Duser.language=pt -Duser.region=PT
Rem Set LANG=-Duser.language=en -Duser.region=US

Rem Default Local Dir
Set CLASSPATH=.

java -Dswing.defaultlaf=%LF% %LANG% -jar CaetanoSoft.JavaDemoApp.jar
Rem pause
Rem Start javaw -Dswing.defaultlaf=%LF% %LANG% -jar CaetanoSoft.JavaDemoApp.jar
