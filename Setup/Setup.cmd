@Echo Off

Rem Java RE Dir
Set JRE=%ProgramFiles%\Java\jre6

Rem Path
Set PATH=%JRE%\bin;%PATH%

Rem Swing Default Look and Feel (-Dswing.defaultlaf=%LF%)
Set LF=com.sun.java.swing.plaf.windows.WindowsLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.gtk.GTKLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.mac.MacLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.motif.MotifLookAndFeel
Rem Set LF=com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
Rem Set LF=javax.swing.plaf.metal.MetalLookAndFeel

Rem Java Language
Set LANG=
Rem Set LANG=-Duser.language=pt -Duser.region=PT
Rem Set LANG=-Duser.language=en -Duser.region=US

Rem JavaPOS XML Configuration File
Set JPOS=
Rem Set JPOS=-Djpos.config.populatorFile=".\jpos.xml"

Rem Default Local Dir
Set CLASSPATH=.

Rem Sun JavaBeans Activation Framework (not for Java RE 1.6 or Java EE 5.0, or later )
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\activation.jar"

Rem Sun JavaMail
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\mail.jar"

Rem Sun JavaComm
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\common.jar"

Rem EPSON JavaPOS
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\jpos111.jar";"%JRE%\lib\ext\pos.jar";"%JRE%\lib\ext\epsonupos.jar";"%JRE%\lib\ext\uposcommon.jar";"%JRE%\lib\ext\linuxUsbDriver.jar";"%JRE%\lib\ext\xercesImpl.jar";"%JRE%\lib\ext\xml-apis.jar"

Rem MS SQL Server JDBC
Rem SQL Server 2000 (Java RE 1.3)
Rem Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\msbase.jar";"%JRE%\lib\ext\msutil.jar";"%JRE%\lib\ext\mssqlserver.jar"
Rem SQL Server 2000/2005 (Java RE 1.5)
Rem Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\sqljdbc.jar"
Rem SQL Server 2005/2008 (Java RE 1.6)
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\sqljdbc4.jar"

Rem Apache Log4Java
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\log4j.jar"

Rem iText PDF/XLS/HTML/RTF Converter
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\iText-2.1.3.jar";"%JRE%\lib\ext\iText-rtf-2.1.3.jar";"%JRE%\lib\ext\iText-rups-2.1.3.jar"

Rem nachoCalendar
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\nachocalendar-0.23.jar"

Rem ---

Rem Sun Enterprise JavaBeans
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\ejb.zip"

Rem ORO, Inc. Net NetComponents
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\NetComponents.jar"

Rem Java Math Expression Parser
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\jep-2.22.jar"

Rem Kunststoff Look&Feel
Set CLASSPATH=%CLASSPATH%;"%JRE%\lib\ext\kunststoff.jar"

Rem IBM
Set CLASSPATH=%CLASSPATH%;"..\lib\imask.jar";"..\lib\inumeric.jar";"..\lib\itime.jar"

Rem KL Group Inc.
Set CLASSPATH=%CLASSPATH%;"..\lib\jcfield450k.jar"

Rem ebPOS
Set CLASSPATH=%CLASSPATH%;"..\lib\fonts";"..\conf";"..\lib\ebpos.jar"

Rem java|javaw -Dswing.defaultlaf=%LF% %LANG% %JPOS% -jar application.jar