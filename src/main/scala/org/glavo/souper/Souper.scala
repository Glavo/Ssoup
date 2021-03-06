package org.glavo.souper

import java.io.{File, InputStream}
import java.net.URL

import org.glavo.souper.nodes._
import org.glavo.souper.parser.Parser
import org.glavo.souper.safety.Whitelist
import org.jsoup.Jsoup

import org.glavo.souper.Implicits._

/** The core public access point to the souper functionality. */
object Souper {
  def parse(html: String, baseUri: String = "")(implicit parser: Parser): Document =
    Jsoup.parse(html, baseUri, parser.delegate)

  def connect(url: String): Connection = Connection(Jsoup.connect(url))

  def parse(in: File, charsetName: String, baseUri: String): Document =
    Jsoup.parse(in, charsetName, baseUri)

  def parse(in: File, charsetName: String): Document =
    org.glavo.souper.nodes.Implicits.documentWrapper(Jsoup.parse(in, charsetName))

  def parse(in: InputStream, charsetName: String, baseUri: String)(implicit parser: Parser): Document =
    Jsoup.parse(in, charsetName, baseUri, parser.delegate)

  def parseBodyFragment(bodyHtml: String, baseUri: String = ""): Document =
    Jsoup.parseBodyFragment(bodyHtml, baseUri)

  def parse(url: URL, timeoutMillis: Int): Document = Jsoup.parse(url, timeoutMillis)

  def clean(bodyHtml: String, baseUri: String, whitelist: Whitelist): String = Jsoup.clean(bodyHtml, baseUri, whitelist.delegate)

  def clean(bodyHtml: String, whitelist: Whitelist): String = Jsoup.clean(bodyHtml, whitelist.delegate)

  def clean(bodyHtml: String, baseUri: String, whitelist: Whitelist, outputSettings: Document.OutputSettings): String =
    Jsoup.clean(bodyHtml, baseUri, whitelist.delegate, outputSettings)

  def isValid(bodyHtml: String, whitelist: Whitelist): Boolean =
    Jsoup.isValid(bodyHtml, whitelist.delegate)
}
