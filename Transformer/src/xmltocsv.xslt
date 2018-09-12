<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:foo="http://www.wathever.com" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs functx" xmlns:functx="http://www.functx.com">
<xsl:import href="functx.xsl"/>
	<xsl:template match="/">
		<xsl:for-each select="paymentList/payment">
			<xsl:template match="datetime">
				<xsl:value-of select="substring(.,1,2)" />
				<xsl:value-of select="substring(.,4,2)" />
				<xsl:value-of select="substring(.,7,4)" />
				,
				<xsl:value-of select="substring(.,12,2)" />
				<xsl:value-of select="substring(.,15,2)" />
				<xsl:value-of select="substring(.,18,2)" />
				,
			</xsl:template>
			<xsl:if test="currency=858">
				1,
			</xsl:if>
			<xsl:if test="currency=840">
				2,
			</xsl:if>
			<xsl:template match="amount">
				<xsl:value-of
					select="functx:pad-integer-to-length(substring-before(., '.'), 13)" />
				<xsl:value-of select="substring-after(., '.')" />
				,
			</xsl:template>
			<xsl:template match="originAccout">
				<xsl:value-of select="substring-before(., '-')" />
				,
				<xsl:value-of select="functx:pad-string-to-length(substring-after(., '-'),' ', 10)" />
				,
			</xsl:template>
			<xsl:template match="destinationAccount">
				<xsl:value-of select="substring-before(., '-')" />
				,
				<xsl:value-of select="functx:pad-string-to-length(substring-after(., '-'),' ', 10)" />
				,
			</xsl:template>
			<xsl:template match="referenceNumber">
				<xsl:value-of select="functx:pad-string-to-length(.,' ', 20)" />
				,
			</xsl:template>
			<xsl:value-of select="referenceNumber" />
			
			<xsl:template match="additionalInformation">
				<xsl:value-of select="functx:pad-string-to-length(.,' ', 50)" />
				,
			</xsl:template>
			<xsl:text>&#xD;</xsl:text>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>