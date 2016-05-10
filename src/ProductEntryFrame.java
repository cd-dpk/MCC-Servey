package pkg;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ProductEntryFrame extends JFrame {
	
	JCheckBox [] jCheckBoxs;
	JLabel [] jProductLabel;
	List<Product> productList = new ArrayList<Product>();
	Product candidateProduct;
	List<String> candidateProductStringList;
	List<String> candidateProductPriceStringList;
	JTextField categoryJText;
	
	public ProductEntryFrame() {
		super("Product Entry of MCC Servey");
		setCandidateProductList();
		int size=candidateProductStringList.size();
		setLayout(new GridLayout(size+2,2));
		jProductLabel = new JLabel[size];
		jCheckBoxs = new JCheckBox[size];
		for (int i = 0; i < size; i++) {
			jCheckBoxs[i] = new JCheckBox(candidateProductStringList.get(i));
			add(jCheckBoxs[i]);
			jProductLabel[i] = new JLabel("?");
			add(jProductLabel[i]);
			final int number = i;
			jCheckBoxs[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if (jCheckBoxs[number].isSelected()) {
						ProductDialog productDialog = new ProductDialog(new Product(number,candidateProductStringList.get(number),candidateProductPriceStringList.get(number)));
						productDialog.show();					
					} else {
						for (int j=0; j<productList.size(); j++) {
							if (productList.get(j).ID == number) {
								productList.remove(j);
								jProductLabel[number].setText("?");
								break;
							}
						}
					}
				}
			});
		}
		
		categoryJText = new JTextField("2000");
		add(categoryJText);
		JButton okayButton = new JButton("OKAY");
		add(okayButton);
		okayButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (productList.size() != 0) {
					File file = new File("Hello.txt");
					try {
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write("Name \t| Price Range BDT \t| Local/Imported\t| Warranty \t| Monthly Sell \t| Local Brand\n");
						//System.out.println("Name\t| Price Range BDT\t| Local/Imported\t| Warranty\t| Monthly Sell\t| Local Brand");
						int i=0;
						for (Product product : productList) {
							//System.out.println(product);
							fileWriter.write((i+1)+". "+ product+"\n");
							i++;
						}
						fileWriter.close();
						refreshFrame();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					//System.out.println("Please Add Product");
				}
			}
		});
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (productList.size()!=0) {
					refreshFrame();					
				} else {
				//	System.out.println("Nothing to refresh");
				}
			}
		});
		add(refreshButton);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ProductEntryFrame.this.dispose();
			}
		});
		add(exitButton);
	}
	class ProductDialog extends JDialog{
		JLabel lowestPriceJLabel,highestPriceJLabel,warrantyJLabel,monthylySellingJLabel,localProductBrandJLabel,productLocalityJLabel;
		JTextField lowestPriceJText,highestPriceJText,warrantyJText,monthylySellingJText,localProductBrandJText;
		JComboBox productLocalityJComboBox;
		String [] productLocalityArray={"Imported","Local"};
		JButton okay, cancel;
		public ProductDialog(Product product) {
			super(ProductEntryFrame.this,"Product Dialog");
			setDefaultCloseOperation(0);
			final Product candidateProduct = product.toClone();
			setLayout(new GridLayout(8,2));
			JLabel productNameLabel  = new JLabel("Product Name");
			add(productNameLabel);
			JLabel productName = new JLabel(candidateProduct.getProductName());
			add(productName);
			lowestPriceJLabel = new JLabel("Lowest Price");
			add(lowestPriceJLabel);
			lowestPriceJText = new JTextField();
			lowestPriceJText.setText(candidateProduct.lowestPrice);
			add(lowestPriceJText);
			highestPriceJLabel = new JLabel("Highest Price");
			add(highestPriceJLabel);
			highestPriceJText = new JTextField();
			highestPriceJText.setText(categoryJText.getText());
			add(highestPriceJText);
			
			productLocalityJLabel = new JLabel("Local/Imported");
			add(productLocalityJLabel);
			productLocalityJComboBox = new JComboBox(productLocalityArray);
			add(productLocalityJComboBox);
			warrantyJLabel = new JLabel("Warranty");
			add(warrantyJLabel);
			warrantyJText = new JTextField();
			add(warrantyJText);
			monthylySellingJLabel = new JLabel("Monthly Product");
			add(monthylySellingJLabel);
			monthylySellingJText = new JTextField();
			add(monthylySellingJText);
			localProductBrandJLabel = new JLabel("Local Product");
			add(localProductBrandJLabel);
			localProductBrandJText = new JTextField();
			add(localProductBrandJText);
			okay = new JButton("OKAY");
			add(okay);
			cancel = new JButton("CANCEL");
			add(cancel);
			setSize(550, 200);
			okay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					candidateProduct.setLowestPrice(lowestPriceJText.getText());
					candidateProduct.setHighestPrice(highestPriceJText.getText());
					candidateProduct.setProductLocalityString(productLocalityArray[productLocalityJComboBox.getSelectedIndex()]);
					if (warrantyJText.getText().equals("")) {
						candidateProduct.warrantyString="N/A";
					}else{
						candidateProduct.warrantyString=warrantyJText.getText();
					}
					if (monthylySellingJText.getText().equals("")) {
						candidateProduct.monthlySellingString="N/A";
					}else{
						candidateProduct.monthlySellingString=monthylySellingJText.getText();
					}
					if (localProductBrandJText.getText().equals("")) {
						candidateProduct.localProductString="N/A";
					}else{
						candidateProduct.localProductString=localProductBrandJText.getText();
					}
					jProductLabel[candidateProduct.ID].setText(candidateProduct.toString());
					productList.add(candidateProduct);
					ProductDialog.this.dispose();
				}
			});
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					jCheckBoxs[candidateProduct.ID].setSelected(false);
					ProductDialog.this.dispose();
					
				}
			});
		}
	}
	
	public  void newFrame(){
		ProductEntryFrame frame = new ProductEntryFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,700);
		frame.setVisible(true);
	}
	private void refreshFrame(){
		this.dispose();
		newFrame();
	}
	private void setCandidateProductList(){
		candidateProductStringList = new ArrayList<String>();
		candidateProductPriceStringList = new ArrayList<String>();
		candidateProductStringList.add("BIJOY");
		candidateProductPriceStringList.add("150");
		candidateProductStringList.add("Mouse");
		candidateProductPriceStringList.add("400");
		candidateProductStringList.add("Router");
		candidateProductPriceStringList.add("1100");
		candidateProductStringList.add("AntiVirus");
		candidateProductPriceStringList.add("1000");
		candidateProductStringList.add("Cable");
		candidateProductPriceStringList.add("700");
		candidateProductStringList.add("HeadPhone");
		candidateProductPriceStringList.add("700");
		candidateProductStringList.add("Mini Sound");
		candidateProductPriceStringList.add("1100");
		candidateProductStringList.add("Bluetooth Sound");
		candidateProductPriceStringList.add("1100");
		candidateProductStringList.add("Keyboard");
		candidateProductPriceStringList.add("300");
		candidateProductStringList.add("Multiplug");
		candidateProductPriceStringList.add("700");
		candidateProductStringList.add("Card Reader");
		candidateProductPriceStringList.add("100");
		candidateProductStringList.add("A4 Mouse");
		candidateProductPriceStringList.add("400");
		candidateProductStringList.add("A4 Keyboard");
		candidateProductPriceStringList.add("300");
		candidateProductStringList.add("Moniotor");
		candidateProductPriceStringList.add("7K");
		candidateProductStringList.add("Pendrive");
		candidateProductPriceStringList.add("350");
		candidateProductStringList.add("Mouse Pad");
		candidateProductPriceStringList.add("50");
		candidateProductStringList.add("Webcam");
		candidateProductPriceStringList.add("1500");
		candidateProductStringList.add("Speaker");
		candidateProductPriceStringList.add("500");
		candidateProductStringList.add("Casing");
		candidateProductPriceStringList.add("1500");
		candidateProductStringList.add("Laptop");
		candidateProductPriceStringList.add("22k");
		candidateProductStringList.add("Desktop");
		candidateProductPriceStringList.add("20k");
		candidateProductStringList.add("Server");
		candidateProductPriceStringList.add("75k");
		candidateProductStringList.add("All in one");
		candidateProductPriceStringList.add("35k");
		candidateProductStringList.add("WorkStation");
		candidateProductPriceStringList.add("1.5k");
		candidateProductStringList.add("Online UPS");
		candidateProductPriceStringList.add("35k");
		candidateProductStringList.add("Switch");
		candidateProductPriceStringList.add("800");
		candidateProductStringList.add("RAM");
		candidateProductPriceStringList.add("1000");
		candidateProductStringList.add("Cartidge");
		candidateProductPriceStringList.add("1400");
		candidateProductStringList.add("Color");
		candidateProductPriceStringList.add("50");
		candidateProductStringList.add("Drum");
		candidateProductPriceStringList.add("600");
		candidateProductStringList.add("Cord");
		candidateProductPriceStringList.add("100");
		candidateProductStringList.add("Laser Ink");
		candidateProductPriceStringList.add("1500");
		candidateProductStringList.add("Motherboard(Printer)");
		candidateProductPriceStringList.add("2500");
		candidateProductStringList.add("Epson Printer");
		candidateProductPriceStringList.add("3000");
		candidateProductStringList.add("Cannon Printer");
		candidateProductPriceStringList.add("3000");
		candidateProductStringList.add("HP Printer");
		candidateProductPriceStringList.add("2500");
		
	}
	public static void main(String[] args) {
		new ProductEntryFrame().newFrame();
	}

}
