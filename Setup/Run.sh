#!/bin/sh
#

# Java RE Dir
JRE=/usr/lib/jvm/java-6-sun-1.6.0.16
#JRE=/usr/lib/jvm/java-6-openjdk

# Path
unset PATH
PATH=${JRE}/bin:${PATH}
export PATH

# Swing Default Look and Feel
#LF="-Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
#LF="-Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
#LF="-Dswing.defaultlaf=com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
#LF="-Dswing.defaultlaf=com.sun.java.swing.plaf.mac.MacLookAndFeel"
#LF="-Dswing.defaultlaf=com.sun.java.swing.plaf.motif.MotifLookAndFeel"
#LF="-Dswing.defaultlaf=javax.swing.plaf.nimbus.NimbusLookAndFeel
#LF="-Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel"

# Java Language
LANG="-Duser.language=pt -Duser.region=PT"
#LANG="-Duser.language=en -Duser.region=US"

# Default Local Dirunset CLASSPATH
CLASSPATH=.
export CLASSPATH

#java /media/OS/Users/JCaetano/Documents/Visual Studio Projects/Java/SplashScreenDemo/build/classes/TranslationTestes
java $LF $LANG -jar CaetanoSoft.JavaDemoApp.jar
# pause
# javaw -Dswing.defaultlaf=$LF $LANG -jar CaetanoSoft.JavaDemoApp.jar &
#  -cp ${CLASSPATH} 
