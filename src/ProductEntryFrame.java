import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
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
						ProductDialog productDialog = new ProductDialog(new Product(number,candidateProductStringList.get(number)));
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
		
		categoryJText = new JTextField("200");
		add(categoryJText);
		JButton okayButton = new JButton("OKAY");
		add(okayButton);
		okayButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (productList.size() != 0) {
					System.out.println("Name\t| Price Range BDT\t| Local/Imported\t| Warranty\t| Monthly Sell\t| Local Brand");
					for (Product product : productList) {
						System.out.println(product);
					}
					refreshFrame();
				} else {
					System.out.println("Please Add Product");
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
					System.out.println("Nothing to refresh");
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
		JLabel lowestPriceJLabel,warrantyJLabel,monthylySellingJLabel,localProductBrandJLabel,productLocalityJLabel;
		JTextField lowestPriceJText,warrantyJText,monthylySellingJText,localProductBrandJText;
		JComboBox productLocalityJComboBox;
		String [] productLocalityArray={"Local","Imported"};
		JButton okay, cancel;
		public ProductDialog(Product product) {
			super(ProductEntryFrame.this,"Product Dialog");
			setDefaultCloseOperation(0);
			final Product candidateProduct = product.toClone();
			setLayout(new GridLayout(7,2));
			JLabel productNameLabel  = new JLabel("Product Name");
			add(productNameLabel);
			JLabel productName = new JLabel(candidateProduct.getProductName());
			add(productName);
			lowestPriceJLabel = new JLabel("Lowest Price");
			add(lowestPriceJLabel);
			lowestPriceJText = new JTextField();
			add(lowestPriceJText);
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
			setSize(500, 200);
			okay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					candidateProduct.setLowestPrice(lowestPriceJText.getText());
					candidateProduct.setHighestPrice(categoryJText.getText());
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
		frame.setSize(500,500);
		frame.setVisible(true);
	}
	private void refreshFrame(){
		this.dispose();
		newFrame();
	}
	private void setCandidateProductList(){
		candidateProductStringList = new ArrayList<String>();
		candidateProductStringList.add("Mouse");
		candidateProductStringList.add("Router");
		candidateProductStringList.add("AntiVirus");
		candidateProductStringList.add("Cable");
		candidateProductStringList.add("HeadPhone");
		candidateProductStringList.add("Mini Sound");
	}
	public static void main(String[] args) {
		new ProductEntryFrame().newFrame();
	}

}
