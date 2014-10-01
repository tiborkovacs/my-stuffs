alias bs='. /w/scripts/build-selenium.sh'

open() {
	type="portal"
	if [ "$2" = "plugins" ]; then
		type="plugins"
		elif [ "$2" = "bundle" ]; then
			type="bundle"
		elif [ "$2" = "dm" ]; then
			type="dm"
	fi

	if [ -z $1 ]; then
		#cd /l/liferay-deprecated-ee/
		cd /l/selenium/qa/functional/
		elif [ "$1" = "dm" ]; then
			cd /w/Workspace/tools/liferay-support-ee-dm/tools/data-generation/data-manipulator-portlet/
		elif [ "$type" = "bundle" ]; then
			cd /w/Workspace/$type/$1/tomcat-*/
		else
			cd /w/Workspace/$type/liferay-$type-ee-$1/
	fi
}
alias op=open

createDB() {
	db=${1}
	if [ -z ${1} ]; then
		db="";
	fi

	mysql -u root -pliferay -e "drop database if exists lportal_${db};create database lportal_${db} character set utf8; "
}
alias cdb=createDB

convert() {
	if [ -z ${1} ]; then
		echo Usage: convert PORTAL_VERSION
		return
	fi

	op;
	cd selenese-builder/;
	ant clean;
	ant;
	cd ..;

	cd test;
	ant convert-selenium -Davailable.portal.versions=${1};
	cd ..
}
alias convert=convert

createPortalFull() {
	actualPWD=$(pwd)
	ver=${1};
	baseR2=/r/Releases/EE
	baseDir=/l/bundles
	baseDirWin=l:/bundles

	if [ -z ${1} ]; then
		echo Usage: cP PORTAL_VERSION HOTFIX_NUMBER
		return
	elif [ "${ver}" == "6010" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.0sp2-hunsupportqa.xml
		tomcatDir=tomcat-6.0.29
		tomcatZipName=liferay-portal-tomcat-6.0-ee-2.zip
		tomcatZip=${baseR2}/6.0/6.0-ee-6.0.10/${tomcatZipName}
	elif [ "${ver}" == "6011" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.0sp2-hunsupportqa.xml
		tomcatDir=tomcat-6.0.29
		tomcatZipName=liferay-portal-tomcat-6.0-ee-sp1.zip
		tomcatZip=${baseR2}/6.0/6.0-ee-sp1-6.0.11/${tomcatZipName}
	elif [ "${ver}" == "6012" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.0sp2-hunsupportqa.xml
		tomcatDir=tomcat-6.0.32
		tomcatZipName=liferay-portal-tomcat-6.0-ee-sp2-20110727.zip
		tomcatZip=${baseR2}/6.0/6.0-ee-sp2-6.0.12/${tomcatZipName}
	elif [ "${ver}" == "6110" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.1ga1-hunsupportqa.xml
		tomcatDir=tomcat-7.0.25
		tomcatZipName=liferay-portal-tomcat-6.1.10-ee-ga1-20120223174854827.zip
		tomcatZip=${baseR2}/6.1/6.1.10-ee-ga1/${tomcatZipName}
	elif [ "${ver}" == "6120" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.1ga2-hunsupportqa.xml
		tomcatDir=tomcat-7.0.27
		tomcatZipName=liferay-portal-tomcat-6.1.20-ee-ga2-20120731110418084.zip
		tomcatZip=${baseR2}/6.1/6.1.20-ee-ga2/${tomcatZipName}
	elif [ "${ver}" == "6130" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.1ga2-hunsupportqa.xml
		tomcatDir=tomcat-7.0.40
		tomcatZipName=liferay-portal-tomcat-6.1.30-ee-ga3-20130812170130063.zip
		tomcatZip=${baseR2}/6.1/6.1.30-ee-ga3/${tomcatZipName}
	elif [ "${ver}" == "6210" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.2ga1-liferaycom.xml
		tomcatDir=tomcat-7.0.42
		tomcatZipName=liferay-portal-tomcat-6.2.10.1-ee-ga1-20131126141110470.zip
		tomcatZip=${baseR2}/6.2/6.2.10.1-ee-ga1/${tomcatZipName}
	elif [ "${ver}" == "6210sp6" ]; then
		portalLicenseXML=license-portaldevelopment-developer-cluster-6.2ga1-liferaycom.xml
		tomcatDir=tomcat-7.0.42
		tomcatZipName=liferay-portal-tomcat-6.2-ee-sp6-20140703103932543.zip
		tomcatZip=${baseR2}/6.2/6.2-ee-sp6/${tomcatZipName}
	fi

	mkdir -p ${baseDir}/
	cd ${baseDir}/

	if [ -z ${2} ]; then
		portalHomeDir=${ver}
	else
		portalHomeDir=${ver}-patched
	fi

	if [ ! -d "${portalHomeDir}/${tomcatDir}" ]; then
		if [ ! -f "${tomcatZipName}" ]; then
			echo get \'${tomcatZipName}\'...

			cp ${tomcatZip} ${tomcatZipName}
		fi

		echo Unzipping bundle: \'${tomcatZipName}\' .... to \'${portalHomeDir}/\'

		unzip -qq ${tomcatZipName} \*/tomcat\*/\* -d ${baseDir}/${portalHomeDir}

		mv ${portalHomeDir}/liferay-portal-*/tomcat-*/ ${portalHomeDir}/
		rm -R ${portalHomeDir}/liferay-portal-*

		portalExtPath=${portalHomeDir}/${tomcatDir}/webapps/ROOT/WEB-INF/classes
		portalExtFile=${portalExtPath}/portal-ext.properties

		echo Create portal-ext.properties with DB access.
		mkdir -p ${portalExtPath}
		echo "jdbc.default.driverClassName=com.mysql.jdbc.Driver" > ${portalExtFile}
		echo "jdbc.default.url=jdbc:mysql://localhost/lportal_${ver}?useUnicode=true&characterEncoding=UTF-8&useFastDataParsing=false" >> ${portalExtFile}
		echo "jdbc.default.username=root" >> ${portalExtFile}
		echo "jdbc.default.password=liferay" >> ${portalExtFile}

		portalSetupWizardFile=${portalHomeDir}/portal-setup-wizard.properties

		echo Create portal-setup-wizard.properties
		echo "admin.email.from.name=Test Test" > ${portalSetupWizardFile}
		echo "liferay.home=${baseDirWin}/${ver}" >> ${portalSetupWizardFile}
		echo "admin.email.from.address=test@liferay.com" >> ${portalSetupWizardFile}
		echo "setup.wizard.enabled=false" >> ${portalSetupWizardFile}

		mkdir -p ${portalHomeDir}/deploy/
		cp /l/licenses/${portalLicenseXML} ${portalHomeDir}/deploy/
	else
		echo The portal already exist.
	fi

	echo Create mysql database
	cdb ${ver}

	if [ ! -z ${2} ]; then
		rm -R ${baseDir}/${portalHomeDir}/patching-tool

		unzip -qq /l/bundles/patching-tool-17-internal.zip -d ${baseDir}/${portalHomeDir}
		cd ${baseDir}/${portalHomeDir}/patching-tool

		patching-tool.sh auto-discovery
		patching-tool.sh download hotfix-${2}-${ver}
		patching-tool.sh install -force
	fi

	cd ${baseDir}/${portalHomeDir}/${tomcatDir}/bin
	start cmd /k catalina.bat start
	cd ${actualPWD}
}
alias cP=createPortalFull



korte() {
	if [ -z ${2} ] || [ -z ${1} ]; then
		echo "Use: korte PORTAL_VERSION TEST_NAME";
		return;
	fi

	mkdir -p /C/Users/KTibi/bundles/patching-tool/patches

	op

	ant -f build-test-properties.xml prepare-test.properties -Dportal.version=${1:0:1}.${1:1:1}.${1:2:2}

	cd test-builder/;ant;cd ..;
	cd test-src;ant -Davailable.portal.versions=${1};cd ..;

	if [ "$3" == "skip" ]; then
		ant -f build-test-selenium.xml run-selenium-test -Dskip.prepare-bundle=true -Dtest.browser=chrome -Dportal.version=${1:0:1}.${1:1:1}.${1:2:2} -Dtest.lp.version=${1} -Dtest.name=${2}
	else
		ant -f build-test-selenium.xml run-selenium-test -Dtest.browser=chrome -Dportal.version=${1:0:1}.${1:1:1}.${1:2:2} -Dtest.lp.version=${1} -Dtest.name=${2} -Dsikuli.debug=3
	fi
}
alias korte=korte
