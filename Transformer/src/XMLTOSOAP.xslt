<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" />


    <xsl:template match="/">
        <root>
            <xsl:apply-templates/>
        </root>
    </xsl:template>


    <xsl:template match="name">
        <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
            <soap:Body>
                <CreateOrganization xmlns="http://provisioning.microsoft.com/webservice/HostedActiveDirectory">
                    <CreateOrganizationRequest xmlns="http://provisioning.microsoft.com/hostedactivedirectory">
                        <Data>
                            <container>test</container>
                            <name>
                                <xsl:value-of select="."/>
                            </name>
                            <preferredDomainController>test</preferredDomainController>
                            <planName />
                            <description>test</description>
                            <properties />
                        </Data>
                    </CreateOrganizationRequest>
                    <sendCredentials>boolean</sendCredentials>
                </CreateOrganization>
            </soap:Body>
        </soap:Envelope>
    </xsl:template>
</xsl:stylesheet>