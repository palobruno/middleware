<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:value-of select="paymentList/id"/>, 
		<xsl:for-each select="paymentList/payment">
			<xsl:value-of select="referenceNumber"/>, 
			<xsl:value-of select="datetime"/>, 
			<xsl:value-of select="currency"/>, 
			<xsl:value-of select="amount"/>,
                        <xsl:value-of select="bank"/>, 
			<xsl:value-of select="originAccout"/>, 
			<xsl:value-of select="destinationAccount"/>, 
			<xsl:value-of select="additionalInformation"/><xsl:text>&#xD;</xsl:text>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>