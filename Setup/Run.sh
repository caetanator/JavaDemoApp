#!/bin/sh

# Set the Java RE VM directory
if [ "$JRE_HOME" != "" ]; then
	cs_JRE=$JRE_HOME
	cs_EMUVER=""
elif [ "$JAVA_HOME" != "" ]; then
	cs_JRE=$JAVA_HOME
	cs_EMUVER=""
else
	# list directories in the form "/tmp/dirname/"
	for dir in /usr/lib/jvm/*/; do
		# remove the trailing "/"
		dir=${dir%*/}
		# print everything after the final "/"
		echo "${dir##*/}"
	done
	if [ -d "/usr/lib/jvm/java-6-sun-1.6.0.45" ] || \
	   [ -d "/usr/lib/jvm/java-6-sun-1.6.0.16" ]; then
		#echo "Java v1.6 detected!"
		cs_JRE=/usr/lib/jvm/java-6-sun-1.6.0.45
		cs_EMUVER=""
	elif [ -d "/usr/lib/jvm/java-7-openjdk" ]; then
		#echo "Java v1.6 detected!"
		cs_JRE=/usr/lib/jvm/java-7-openjdk
		cs_EMUVER="-Djava.version=1.6"
	else
			echo "ERRO: VM Java RE v1.6 ou v1.5 não encontrada!"
			exit -1
	fi
fi
## UNIX/LINUX
#cs_JRE=/usr/lib/jvm/java-6-sun-1.6.0.16
cs_JRE=/usr/lib/jvm/java-6-sun-1.6.0.45
#cs_JRE=/usr/lib/jvm/java-6-openjdk
#cs_JRE=${JRE_HOME}
#cs_JRE=${JAVA_HOME}

# Set the application path
unset PATH
PATH=${cs_JRE}/bin:${PATH}
export PATH

# Set the JRE emulated version
cs_EMUVER=""
#cs_EMUVER="-Djava.version=1.6"

# Set the default Java Class Path to the application local directory
unset CLASSPATH
CLASSPATH=.
export CLASSPATH

# Set the Java Swing default look and feel
cs_LaF=""
#cs_LaF="-Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
#cs_LaF="-Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
#cs_LaF="-Dswing.defaultlaf=com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
#cs_LaF="-Dswing.defaultlaf=com.sun.java.swing.plaf.mac.MacLookAndFeel"
#cs_LaF="-Dswing.defaultlaf=com.sun.java.swing.plaf.motif.MotifLookAndFeel"
#cs_LaF="-Dswing.defaultlaf=javax.swing.plaf.nimbus.NimbusLookAndFeel"
#cs_LaF="-Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel"

# Set the Java international language and region
cs_LangReg=""
#cs_LangReg="-Duser.language=pt -Duser.region=PT"
#cs_LangReg="-Duser.language=en -Duser.region=US"

# Console Default Code Page
cs_CP=""
# ISO 8859-1 (Latin-1)
#cs_CP=-Dfile.encoding=819
# ISO 8859-15 (Latin-9) (Euro support)
#cs_CP=-Dfile.encoding=923
# OEM Latin-1
#cs_CP=-Dfile.encoding=850
# OEM Portugese
#cs_CP=-Dfile.encoding=860
# Windows Latin-1
#cs_CP=-Dfile.encoding=1252
# EBCDIC Latin-1 (MNCS)
#cs_CP="-Dfile.encoding=Cp500"
# EBCDIC Latin-1 (MNCS) (Euro support)
#cs_CP=-Dfile.encoding=1148
# EBCDIC United States and others
#cs_CP=-Dfile.encoding=37
# EBCDIC United States and others (Euro support)
#cs_CP=-Dfile.encoding=1140
# Unicode AS/400 Unicode (used primarily in the IFS)
#cs_CP=-Dfile.encoding=61952

# Run the application
${cs_JRE}/bin/java ${cs_EMUVER} ${cs_LaF} ${cs_LangReg} ${cs_CP} -cp "${CLASSPATH}" -jar CaetanoSoft.JavaDemoApp.jar
#${cs_JRE}/bin/javaw ${cs_EMUVER} ${cs_LaF} ${cs_LangReg} ${cs_CP} -cp "${CLASSPATH}" -jar CaetanoSoft.JavaDemoApp.jar &

# Clean up system variables
cs_JRE=
cs_EMUVER=
cs_LangReg=
cs_LaF=
cs_CP=
#read -p "Press [Enter] key"
