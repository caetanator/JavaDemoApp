@Echo Off
If "%ProgramFiles(x86)%" == "" Set ProgramFiles(x86)=%ProgramFiles%
Set jdk=%ProgramFiles(x86)%\Java\jdk1.6.0_45
Set jre=%ProgramFiles(x86)%\Java\jre6
Set _Dir=%CD%
Echo %_Dir%
Rem Java Developers Kit
If Exist "%jdk%" (
	Rem Java Communications API Windows OS driver.
	Copy /Y ".\win32com.dll" "%jdk%\jre\bin\"
	Rem Java Communications API standard extension.
	Copy /Y ".\comm.jar" "%jdk%\jre\lib\ext\"
	Rem Java Communications API driver configuration.
	Rem Must implement the interface javax.comm.CommDriver
	Copy /Y ".\javax.comm.properties" "%jdk%\jre\lib\"
)
Rem Java Run-time Environment
If Exist "%jre%" (
	Rem Java Communications API Windows OS driver.
	Copy /Y ".\win32com.dll" "%jre%\bin\"
	Rem Java Communications API standard extension.
	Copy /Y ".\comm.jar" "%jre%\lib\ext\"
	Rem Java Communications API driver configuration.
	Rem Must implement the interface javax.comm.CommDriver
	Copy /Y ".\javax.comm.properties" "%jre%\lib\"
)
pause
