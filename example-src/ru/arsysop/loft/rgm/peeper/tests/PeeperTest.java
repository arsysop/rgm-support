/*
 * Copyright (c) ArSysOp 2020-2025
 * 
 * ArSysOp and its affiliates make no warranty of any kind
 * with regard to this material.
 * 
 * ArSysOp expressly disclaims all warranties as to the material, express,
 * and implied, including but not limited to the implied warranties of
 * merchantability, fitness for a particular purpose and non-infringement of third
 * party rights.
 * 
 * In no event shall ArSysOp be liable to you or any other person for any damages,
 * including, without limitation, any direct, indirect, incidental or consequential
 * damages, expenses, lost profits, lost data or other damages arising out of the use,
 * misuse or inability to use the material and any derived software, even if ArSysOp,
 * its affiliate or an authorized dealer has been advised of the possibility of such damages.
 *
 */

package ru.arsysop.loft.rgm.peeper.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

import ru.arsysop.loft.rgm.spec.model.api.Document;
import ru.arsysop.loft.rgm.spec.model.api.Section;
import ru.arsysop.loft.rgm.spec.model.api.Toc;
import ru.arsysop.loft.rgm.spec.model.api.TocChapter;

public final class PeeperTest {

	@Test
	public void readSpecEn() {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		File specen = new File("cpp17_cpp17.specen"); //$NON-NLS-1$
		URI uri = URI.createFileURI(specen.getAbsolutePath());
		Resource resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		List<EObject> contents = resource.getContents();
		assertFalse(contents.isEmpty());
		Document document = (Document) contents.get(0);
		readToc(document);
		readSections(document);
	}

	private void readToc(Document document) {
		Toc toc = document.getToc();
		assertNotNull(toc);
		List<TocChapter> chapters = toc.getChapters();
		assertFalse(chapters.isEmpty());
		readAnyTocChapter(chapters);
	}

	private void readAnyTocChapter(List<TocChapter> chapters) {
		TocChapter chapter = fullFeatherChapters(chapters);
		// DOC: Identifier is not memory-encrypted
		assertTrue(chapter.getId().equals(chapter.getIdDecrypted()));
		// DOC: Most of other attributes are memory-encrypted;
		// DOC: use get*Decrypted() methods to access business value
		assertFalse(chapter.getName().equals(chapter.getNameDecrypted()));
		assertFalse(chapter.getNumber().equals(chapter.getNumberDecrypted()));
	}

	private void readSections(Document document) {
		List<Section> sections = document.getSections();
		assertFalse(sections.isEmpty());
		readAnySection(sections);
	}

	private void readAnySection(List<Section> sections) {
		Section section = fullFeatherSection(sections);
		// DOC: Identifier is not memory-encrypted
		assertTrue(section.getId().equals(section.getIdDecrypted()));
		// DOC: Most of other attributes are memory-encrypted;
		// DOC: use get*Decrypted() methods to access business value
		assertFalse(section.getName().equals(section.getNameDecrypted()));
		assertFalse(section.getNumber().equals(section.getNumberDecrypted()));
		assertFalse(section.getLocation().equals(section.getLocationDecrypted()));
	}

	private Section fullFeatherSection(List<Section> sections) {
		return sections.stream()//
				.filter(this::fullFeather)//
				.findAny()//
				.get();
	}

	private TocChapter fullFeatherChapters(List<TocChapter> chapters) {
		return chapters.stream()//
				.filter(this::fullFeather)//
				.findAny()//
				.get();
	}

	private boolean fullFeather(TocChapter chapter) {
		return valuable(chapter.getId())//
				&& valuable(chapter.getName())//
				&& valuable(chapter.getNumber());
	}

	private boolean fullFeather(Section section) {
		return valuable(section.getId())//
				&& valuable(section.getName())//
				&& valuable(section.getNumber())//
				&& valuable(section.getLocation());
	}

	private boolean valuable(String value) {
		return value != null && !value.isBlank();
	}

}
