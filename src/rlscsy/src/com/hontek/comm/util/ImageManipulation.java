package com.hontek.comm.util;

import java.util.Iterator;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
/**
 * 设置图片DPI
 * @author dream
 *
 */
public class ImageManipulation {

	private BufferedImage gridImage;
	private int DPI = 300;//默认为300DPI

	public BufferedImage getGridImage() {
		return gridImage;
	}

	public void setGridImage(BufferedImage gridImage) {
		this.gridImage = gridImage;
	}
	
	public int getDPI() {
		return DPI;
	}

	public void setDPI(int dPI) {
		DPI = dPI;
	}

	/**
	 * 保存文件
	 * @param output
	 * @throws IOException
	 */
	public void saveGridImage(File output) throws IOException {

		final String formatName = "png";

		for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName(formatName); iw.hasNext();) {
			ImageWriter writer = iw.next();
			ImageWriteParam writeParam = writer.getDefaultWriteParam();
			ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
			IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
			if (metadata.isReadOnly()|| !metadata.isStandardMetadataFormatSupported()) {
				continue;
			}

			setDPI(metadata);

			final ImageOutputStream stream = ImageIO.createImageOutputStream(output);
			try {
				writer.setOutput(stream);
				writer.write(metadata, new IIOImage(gridImage, null, metadata),writeParam);
			} finally {
				stream.close();
			}
			break;
		}
	}

	private void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {

		// for PMG, it's dots per millimeter
		double dotsPerMilli = 1.0 * DPI / 10 / 2.54;

		IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
		horiz.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
		vert.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode dim = new IIOMetadataNode("Dimension");
		dim.appendChild(horiz);
		dim.appendChild(vert);

		IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
		root.appendChild(dim);

		metadata.mergeTree("javax_imageio_1.0", root);
	}
}