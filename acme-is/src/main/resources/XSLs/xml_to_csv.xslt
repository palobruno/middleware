<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" encoding="utf-8" />

<xsl:param name="delim" select="','" />
<xsl:param name="break" select="'&#xA;'" />

<xsl:template match="/">
    <xsl:for-each select="paymentList/payment">
        <xsl:value-of select="substring(datetime,1,2)" />
        <xsl:value-of select="substring(datetime,4,2)" />
        <xsl:value-of select="substring(datetime,7,4)" />
        <xsl:value-of select="$delim" />
        <xsl:value-of select="substring(datetime,12,2)" />
        <xsl:value-of select="substring(datetime,15,2)" />
        <xsl:value-of select="substring(datetime,18,2)" />
        <xsl:value-of select="$delim" />
        <xsl:choose>
            <xsl:when test="currency=858">
                <xsl:text>1</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>2</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:value-of select="$delim" />
        <xsl:value-of select="format-number(substring-before(amount, '.'), '0000000000000')" />
		<xsl:value-of select="substring-after(amount, '.')" />
		<xsl:value-of select="$delim" />
		<xsl:value-of select="substring-before(originAccout, '-')" />
		<xsl:value-of select="$delim" />
		<xsl:value-of select="substring(concat('          ', substring-after(originAccout, '-')), 1 + string-length(substring-after(originAccout, '-')))" />
        <xsl:value-of select="$delim" />
        <xsl:value-of select="substring-before(destinationAccount, '-')" />
		<xsl:value-of select="$delim" />
        <xsl:value-of select="substring(concat('          ', substring-after(destinationAccount, '-')), 1 + string-length(substring-after(destinationAccount, '-')))" />
        <xsl:value-of select="$delim" />
        <xsl:value-of select="substring(concat(referenceNumber, '                    '), 1, 20)" />
        <xsl:value-of select="$delim" />
        <xsl:value-of select="substring(concat(additionalInformation, '                                                  '), 1, 50)" />
		<xsl:value-of select="$break" />
    </xsl:for-each>
</xsl:template>

</xsl:stylesheet>