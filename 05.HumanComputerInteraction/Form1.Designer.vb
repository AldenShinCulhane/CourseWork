<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Dim DataGridViewCellStyle6 As DataGridViewCellStyle = New DataGridViewCellStyle()
        Dim DataGridViewCellStyle7 As DataGridViewCellStyle = New DataGridViewCellStyle()
        Dim DataGridViewCellStyle8 As DataGridViewCellStyle = New DataGridViewCellStyle()
        Dim DataGridViewCellStyle9 As DataGridViewCellStyle = New DataGridViewCellStyle()
        Dim DataGridViewCellStyle10 As DataGridViewCellStyle = New DataGridViewCellStyle()
        TabControl = New TabControl()
        HomePage = New TabPage()
        RecipeGroup = New GroupBox()
        HomeDataGridRecipes = New DataGridView()
        HomeRecipeLists = New DataGridViewTextBoxColumn()
        RecipeLabel = New Label()
        ListGroup = New GroupBox()
        HomeDataGridLists = New DataGridView()
        HomeListNames = New DataGridViewTextBoxColumn()
        ListLabel = New Label()
        NotificationGroup = New GroupBox()
        HomeDataGridNotification = New DataGridView()
        HomeAlert = New DataGridViewTextBoxColumn()
        HomeTime = New DataGridViewTextBoxColumn()
        NotificationLabel = New Label()
        InventoryGroup = New GroupBox()
        HomeDataGridInventory = New DataGridView()
        HomeItem = New DataGridViewTextBoxColumn()
        HomeExpire = New DataGridViewTextBoxColumn()
        HomeQuantity = New DataGridViewTextBoxColumn()
        InventoryLabel = New Label()
        InventoryPage = New TabPage()
        InventoryDataGrid = New DataGridView()
        Item = New DataGridViewTextBoxColumn()
        ExpirationDate = New DataGridViewTextBoxColumn()
        Quantity = New DataGridViewTextBoxColumn()
        Edit = New DataGridViewImageColumn()
        Delete = New DataGridViewImageColumn()
        ListPage = New TabPage()
        ListDataGrid = New DataGridView()
        DataGridViewTextBoxColumn1 = New DataGridViewTextBoxColumn()
        DataGridViewTextBoxColumn2 = New DataGridViewTextBoxColumn()
        DataGridViewImageColumn1 = New DataGridViewImageColumn()
        DataGridViewImageColumn2 = New DataGridViewImageColumn()
        RecipePage = New TabPage()
        RecipesDataGrid = New DataGridView()
        ShoppersPage = New TabPage()
        DataGridView1 = New DataGridView()
        DataGridViewTextBoxColumn3 = New DataGridViewTextBoxColumn()
        DataGridViewTextBoxColumn7 = New DataGridViewTextBoxColumn()
        DataGridViewTextBoxColumn8 = New DataGridViewTextBoxColumn()
        DataGridViewImageColumn5 = New DataGridViewImageColumn()
        DataGridViewImageColumn6 = New DataGridViewImageColumn()
        NotificationsPage = New TabPage()
        NotificationDataGrid = New DataGridView()
        HelpPage = New TabPage()
        DataGridViewTextBoxColumn4 = New DataGridViewTextBoxColumn()
        DataGridViewTextBoxColumn6 = New DataGridViewTextBoxColumn()
        DataGridViewImageColumn3 = New DataGridViewImageColumn()
        DataGridViewImageColumn4 = New DataGridViewImageColumn()
        DataGridViewTextBoxColumn9 = New DataGridViewTextBoxColumn()
        DataGridViewTextBoxColumn10 = New DataGridViewTextBoxColumn()
        TabControl.SuspendLayout()
        HomePage.SuspendLayout()
        RecipeGroup.SuspendLayout()
        CType(HomeDataGridRecipes, ComponentModel.ISupportInitialize).BeginInit()
        ListGroup.SuspendLayout()
        CType(HomeDataGridLists, ComponentModel.ISupportInitialize).BeginInit()
        NotificationGroup.SuspendLayout()
        CType(HomeDataGridNotification, ComponentModel.ISupportInitialize).BeginInit()
        InventoryGroup.SuspendLayout()
        CType(HomeDataGridInventory, ComponentModel.ISupportInitialize).BeginInit()
        InventoryPage.SuspendLayout()
        CType(InventoryDataGrid, ComponentModel.ISupportInitialize).BeginInit()
        ListPage.SuspendLayout()
        CType(ListDataGrid, ComponentModel.ISupportInitialize).BeginInit()
        RecipePage.SuspendLayout()
        CType(RecipesDataGrid, ComponentModel.ISupportInitialize).BeginInit()
        ShoppersPage.SuspendLayout()
        CType(DataGridView1, ComponentModel.ISupportInitialize).BeginInit()
        NotificationsPage.SuspendLayout()
        CType(NotificationDataGrid, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' TabControl
        ' 
        TabControl.Controls.Add(HomePage)
        TabControl.Controls.Add(InventoryPage)
        TabControl.Controls.Add(ListPage)
        TabControl.Controls.Add(RecipePage)
        TabControl.Controls.Add(ShoppersPage)
        TabControl.Controls.Add(NotificationsPage)
        TabControl.Controls.Add(HelpPage)
        TabControl.Location = New Point(0, 0)
        TabControl.Name = "TabControl"
        TabControl.SelectedIndex = 0
        TabControl.Size = New Size(800, 450)
        TabControl.TabIndex = 0
        ' 
        ' HomePage
        ' 
        HomePage.BackColor = Color.FromArgb(CByte(224), CByte(224), CByte(224))
        HomePage.Controls.Add(RecipeGroup)
        HomePage.Controls.Add(ListGroup)
        HomePage.Controls.Add(NotificationGroup)
        HomePage.Controls.Add(InventoryGroup)
        HomePage.Location = New Point(4, 24)
        HomePage.Name = "HomePage"
        HomePage.Padding = New Padding(3)
        HomePage.Size = New Size(792, 422)
        HomePage.TabIndex = 0
        HomePage.Text = "Home"
        ' 
        ' RecipeGroup
        ' 
        RecipeGroup.BackColor = Color.White
        RecipeGroup.Controls.Add(HomeDataGridRecipes)
        RecipeGroup.Controls.Add(RecipeLabel)
        RecipeGroup.Location = New Point(403, 214)
        RecipeGroup.Name = "RecipeGroup"
        RecipeGroup.Size = New Size(380, 190)
        RecipeGroup.TabIndex = 3
        RecipeGroup.TabStop = False
        ' 
        ' HomeDataGridRecipes
        ' 
        HomeDataGridRecipes.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        HomeDataGridRecipes.Columns.AddRange(New DataGridViewColumn() {HomeRecipeLists})
        DataGridViewCellStyle6.Alignment = DataGridViewContentAlignment.MiddleLeft
        DataGridViewCellStyle6.BackColor = SystemColors.Window
        DataGridViewCellStyle6.Font = New Font("Segoe UI", 9F, FontStyle.Regular, GraphicsUnit.Point)
        DataGridViewCellStyle6.ForeColor = SystemColors.ControlText
        DataGridViewCellStyle6.SelectionBackColor = Color.Transparent
        DataGridViewCellStyle6.SelectionForeColor = SystemColors.ControlText
        DataGridViewCellStyle6.WrapMode = DataGridViewTriState.False
        HomeDataGridRecipes.DefaultCellStyle = DataGridViewCellStyle6
        HomeDataGridRecipes.Location = New Point(16, 22)
        HomeDataGridRecipes.Name = "HomeDataGridRecipes"
        HomeDataGridRecipes.RowTemplate.Height = 25
        HomeDataGridRecipes.Size = New Size(349, 150)
        HomeDataGridRecipes.TabIndex = 2
        ' 
        ' HomeRecipeLists
        ' 
        HomeRecipeLists.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        HomeRecipeLists.HeaderText = "Recipes"
        HomeRecipeLists.Name = "HomeRecipeLists"
        ' 
        ' RecipeLabel
        ' 
        RecipeLabel.AutoSize = True
        RecipeLabel.Location = New Point(6, 0)
        RecipeLabel.Name = "RecipeLabel"
        RecipeLabel.Size = New Size(47, 15)
        RecipeLabel.TabIndex = 3
        RecipeLabel.Text = "Recipes"
        ' 
        ' ListGroup
        ' 
        ListGroup.BackColor = Color.White
        ListGroup.Controls.Add(HomeDataGridLists)
        ListGroup.Controls.Add(ListLabel)
        ListGroup.Location = New Point(9, 214)
        ListGroup.Name = "ListGroup"
        ListGroup.Size = New Size(380, 190)
        ListGroup.TabIndex = 2
        ListGroup.TabStop = False
        ' 
        ' HomeDataGridLists
        ' 
        HomeDataGridLists.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        HomeDataGridLists.Columns.AddRange(New DataGridViewColumn() {HomeListNames})
        DataGridViewCellStyle7.Alignment = DataGridViewContentAlignment.MiddleLeft
        DataGridViewCellStyle7.BackColor = SystemColors.Window
        DataGridViewCellStyle7.Font = New Font("Segoe UI", 9F, FontStyle.Regular, GraphicsUnit.Point)
        DataGridViewCellStyle7.ForeColor = SystemColors.ControlText
        DataGridViewCellStyle7.SelectionBackColor = Color.White
        DataGridViewCellStyle7.SelectionForeColor = SystemColors.ControlText
        DataGridViewCellStyle7.WrapMode = DataGridViewTriState.False
        HomeDataGridLists.DefaultCellStyle = DataGridViewCellStyle7
        HomeDataGridLists.Location = New Point(15, 22)
        HomeDataGridLists.Name = "HomeDataGridLists"
        HomeDataGridLists.RowTemplate.Height = 25
        HomeDataGridLists.Size = New Size(349, 150)
        HomeDataGridLists.TabIndex = 2
        ' 
        ' HomeListNames
        ' 
        HomeListNames.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        HomeListNames.HeaderText = "Shopping Lists"
        HomeListNames.Name = "HomeListNames"
        ' 
        ' ListLabel
        ' 
        ListLabel.AutoSize = True
        ListLabel.Location = New Point(6, 0)
        ListLabel.Name = "ListLabel"
        ListLabel.Size = New Size(89, 15)
        ListLabel.TabIndex = 1
        ListLabel.Text = "Shoppings Lists"
        ' 
        ' NotificationGroup
        ' 
        NotificationGroup.BackColor = Color.White
        NotificationGroup.Controls.Add(HomeDataGridNotification)
        NotificationGroup.Controls.Add(NotificationLabel)
        NotificationGroup.Location = New Point(403, 18)
        NotificationGroup.Name = "NotificationGroup"
        NotificationGroup.Size = New Size(380, 190)
        NotificationGroup.TabIndex = 1
        NotificationGroup.TabStop = False
        ' 
        ' HomeDataGridNotification
        ' 
        HomeDataGridNotification.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        HomeDataGridNotification.Columns.AddRange(New DataGridViewColumn() {HomeAlert, HomeTime})
        DataGridViewCellStyle8.Alignment = DataGridViewContentAlignment.MiddleLeft
        DataGridViewCellStyle8.BackColor = SystemColors.Window
        DataGridViewCellStyle8.Font = New Font("Segoe UI", 9F, FontStyle.Regular, GraphicsUnit.Point)
        DataGridViewCellStyle8.ForeColor = SystemColors.ControlText
        DataGridViewCellStyle8.SelectionBackColor = Color.White
        DataGridViewCellStyle8.SelectionForeColor = SystemColors.ControlText
        DataGridViewCellStyle8.WrapMode = DataGridViewTriState.False
        HomeDataGridNotification.DefaultCellStyle = DataGridViewCellStyle8
        HomeDataGridNotification.Location = New Point(16, 22)
        HomeDataGridNotification.Name = "HomeDataGridNotification"
        DataGridViewCellStyle9.Alignment = DataGridViewContentAlignment.MiddleLeft
        DataGridViewCellStyle9.BackColor = SystemColors.Control
        DataGridViewCellStyle9.Font = New Font("Segoe UI", 9F, FontStyle.Regular, GraphicsUnit.Point)
        DataGridViewCellStyle9.ForeColor = SystemColors.WindowText
        DataGridViewCellStyle9.SelectionBackColor = Color.Transparent
        DataGridViewCellStyle9.SelectionForeColor = SystemColors.HighlightText
        DataGridViewCellStyle9.WrapMode = DataGridViewTriState.True
        HomeDataGridNotification.RowHeadersDefaultCellStyle = DataGridViewCellStyle9
        HomeDataGridNotification.RowTemplate.Height = 25
        HomeDataGridNotification.Size = New Size(349, 150)
        HomeDataGridNotification.TabIndex = 2
        ' 
        ' HomeAlert
        ' 
        HomeAlert.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        HomeAlert.HeaderText = "Alert"
        HomeAlert.Name = "HomeAlert"
        ' 
        ' HomeTime
        ' 
        HomeTime.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        HomeTime.HeaderText = "Time"
        HomeTime.Name = "HomeTime"
        ' 
        ' NotificationLabel
        ' 
        NotificationLabel.AutoSize = True
        NotificationLabel.Location = New Point(6, 0)
        NotificationLabel.Name = "NotificationLabel"
        NotificationLabel.Size = New Size(75, 15)
        NotificationLabel.TabIndex = 2
        NotificationLabel.Text = "Notifications"
        ' 
        ' InventoryGroup
        ' 
        InventoryGroup.BackColor = Color.White
        InventoryGroup.Controls.Add(HomeDataGridInventory)
        InventoryGroup.Controls.Add(InventoryLabel)
        InventoryGroup.Location = New Point(9, 18)
        InventoryGroup.Name = "InventoryGroup"
        InventoryGroup.Size = New Size(380, 190)
        InventoryGroup.TabIndex = 0
        InventoryGroup.TabStop = False
        ' 
        ' HomeDataGridInventory
        ' 
        HomeDataGridInventory.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        HomeDataGridInventory.Columns.AddRange(New DataGridViewColumn() {HomeItem, HomeExpire, HomeQuantity})
        DataGridViewCellStyle10.Alignment = DataGridViewContentAlignment.MiddleLeft
        DataGridViewCellStyle10.BackColor = SystemColors.Window
        DataGridViewCellStyle10.Font = New Font("Segoe UI", 9F, FontStyle.Regular, GraphicsUnit.Point)
        DataGridViewCellStyle10.ForeColor = SystemColors.ControlText
        DataGridViewCellStyle10.SelectionBackColor = Color.White
        DataGridViewCellStyle10.SelectionForeColor = SystemColors.ControlText
        DataGridViewCellStyle10.WrapMode = DataGridViewTriState.False
        HomeDataGridInventory.DefaultCellStyle = DataGridViewCellStyle10
        HomeDataGridInventory.Location = New Point(15, 22)
        HomeDataGridInventory.Name = "HomeDataGridInventory"
        HomeDataGridInventory.RowTemplate.Height = 25
        HomeDataGridInventory.Size = New Size(349, 150)
        HomeDataGridInventory.TabIndex = 1
        ' 
        ' HomeItem
        ' 
        HomeItem.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        HomeItem.HeaderText = "Item"
        HomeItem.Name = "HomeItem"
        ' 
        ' HomeExpire
        ' 
        HomeExpire.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        HomeExpire.HeaderText = "Expiration"
        HomeExpire.Name = "HomeExpire"
        ' 
        ' HomeQuantity
        ' 
        HomeQuantity.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        HomeQuantity.HeaderText = "Quantity"
        HomeQuantity.Name = "HomeQuantity"
        ' 
        ' InventoryLabel
        ' 
        InventoryLabel.AutoSize = True
        InventoryLabel.Location = New Point(6, 0)
        InventoryLabel.Name = "InventoryLabel"
        InventoryLabel.Size = New Size(57, 15)
        InventoryLabel.TabIndex = 0
        InventoryLabel.Text = "Inventory"
        ' 
        ' InventoryPage
        ' 
        InventoryPage.Controls.Add(InventoryDataGrid)
        InventoryPage.Location = New Point(4, 24)
        InventoryPage.Name = "InventoryPage"
        InventoryPage.Padding = New Padding(3)
        InventoryPage.Size = New Size(792, 422)
        InventoryPage.TabIndex = 1
        InventoryPage.Text = "Inventory"
        InventoryPage.UseVisualStyleBackColor = True
        ' 
        ' InventoryDataGrid
        ' 
        InventoryDataGrid.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        InventoryDataGrid.Columns.AddRange(New DataGridViewColumn() {Item, ExpirationDate, Quantity, Edit, Delete})
        InventoryDataGrid.Location = New Point(6, 11)
        InventoryDataGrid.Name = "InventoryDataGrid"
        InventoryDataGrid.RowTemplate.Height = 25
        InventoryDataGrid.Size = New Size(780, 400)
        InventoryDataGrid.TabIndex = 0
        ' 
        ' Item
        ' 
        Item.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        Item.HeaderText = "Item"
        Item.Name = "Item"
        ' 
        ' ExpirationDate
        ' 
        ExpirationDate.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        ExpirationDate.HeaderText = "Expiration Date"
        ExpirationDate.Name = "ExpirationDate"
        ' 
        ' Quantity
        ' 
        Quantity.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        Quantity.HeaderText = "Quantity"
        Quantity.Name = "Quantity"
        ' 
        ' Edit
        ' 
        Edit.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        Edit.HeaderText = ""
        Edit.Image = My.Resources.Resources.draw
        Edit.ImageLayout = DataGridViewImageCellLayout.Zoom
        Edit.Name = "Edit"
        Edit.Resizable = DataGridViewTriState.True
        Edit.SortMode = DataGridViewColumnSortMode.Automatic
        Edit.Width = 50
        ' 
        ' Delete
        ' 
        Delete.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        Delete.HeaderText = ""
        Delete.Image = My.Resources.Resources.trash
        Delete.ImageLayout = DataGridViewImageCellLayout.Zoom
        Delete.Name = "Delete"
        Delete.Resizable = DataGridViewTriState.True
        Delete.SortMode = DataGridViewColumnSortMode.Automatic
        Delete.Width = 50
        ' 
        ' ListPage
        ' 
        ListPage.Controls.Add(ListDataGrid)
        ListPage.Location = New Point(4, 24)
        ListPage.Name = "ListPage"
        ListPage.Padding = New Padding(3)
        ListPage.Size = New Size(792, 422)
        ListPage.TabIndex = 2
        ListPage.Text = "Shopping Lists"
        ListPage.UseVisualStyleBackColor = True
        ' 
        ' ListDataGrid
        ' 
        ListDataGrid.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        ListDataGrid.Columns.AddRange(New DataGridViewColumn() {DataGridViewTextBoxColumn1, DataGridViewTextBoxColumn2, DataGridViewImageColumn1, DataGridViewImageColumn2})
        ListDataGrid.Location = New Point(6, 11)
        ListDataGrid.Name = "ListDataGrid"
        ListDataGrid.RowTemplate.Height = 25
        ListDataGrid.Size = New Size(780, 400)
        ListDataGrid.TabIndex = 1
        ' 
        ' DataGridViewTextBoxColumn1
        ' 
        DataGridViewTextBoxColumn1.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn1.HeaderText = "List"
        DataGridViewTextBoxColumn1.Name = "DataGridViewTextBoxColumn1"
        ' 
        ' DataGridViewTextBoxColumn2
        ' 
        DataGridViewTextBoxColumn2.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn2.HeaderText = "Shopper"
        DataGridViewTextBoxColumn2.Name = "DataGridViewTextBoxColumn2"
        ' 
        ' DataGridViewImageColumn1
        ' 
        DataGridViewImageColumn1.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        DataGridViewImageColumn1.HeaderText = ""
        DataGridViewImageColumn1.Image = My.Resources.Resources.draw
        DataGridViewImageColumn1.ImageLayout = DataGridViewImageCellLayout.Zoom
        DataGridViewImageColumn1.Name = "DataGridViewImageColumn1"
        DataGridViewImageColumn1.Resizable = DataGridViewTriState.True
        DataGridViewImageColumn1.SortMode = DataGridViewColumnSortMode.Automatic
        DataGridViewImageColumn1.Width = 50
        ' 
        ' DataGridViewImageColumn2
        ' 
        DataGridViewImageColumn2.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        DataGridViewImageColumn2.HeaderText = ""
        DataGridViewImageColumn2.Image = My.Resources.Resources.trash
        DataGridViewImageColumn2.ImageLayout = DataGridViewImageCellLayout.Zoom
        DataGridViewImageColumn2.Name = "DataGridViewImageColumn2"
        DataGridViewImageColumn2.Resizable = DataGridViewTriState.True
        DataGridViewImageColumn2.SortMode = DataGridViewColumnSortMode.Automatic
        DataGridViewImageColumn2.Width = 50
        ' 
        ' RecipePage
        ' 
        RecipePage.Controls.Add(RecipesDataGrid)
        RecipePage.Location = New Point(4, 24)
        RecipePage.Name = "RecipePage"
        RecipePage.Padding = New Padding(3)
        RecipePage.Size = New Size(792, 422)
        RecipePage.TabIndex = 3
        RecipePage.Text = "Recipes "
        RecipePage.UseVisualStyleBackColor = True
        ' 
        ' RecipesDataGrid
        ' 
        RecipesDataGrid.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        RecipesDataGrid.Columns.AddRange(New DataGridViewColumn() {DataGridViewTextBoxColumn4, DataGridViewTextBoxColumn6, DataGridViewImageColumn3, DataGridViewImageColumn4})
        RecipesDataGrid.Location = New Point(6, 11)
        RecipesDataGrid.Name = "RecipesDataGrid"
        RecipesDataGrid.RowTemplate.Height = 25
        RecipesDataGrid.Size = New Size(780, 400)
        RecipesDataGrid.TabIndex = 1
        ' 
        ' ShoppersPage
        ' 
        ShoppersPage.Controls.Add(DataGridView1)
        ShoppersPage.Location = New Point(4, 24)
        ShoppersPage.Name = "ShoppersPage"
        ShoppersPage.Padding = New Padding(3)
        ShoppersPage.Size = New Size(792, 422)
        ShoppersPage.TabIndex = 6
        ShoppersPage.Text = "Shoppers"
        ShoppersPage.UseVisualStyleBackColor = True
        ' 
        ' DataGridView1
        ' 
        DataGridView1.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        DataGridView1.Columns.AddRange(New DataGridViewColumn() {DataGridViewTextBoxColumn3, DataGridViewTextBoxColumn7, DataGridViewTextBoxColumn8, DataGridViewImageColumn5, DataGridViewImageColumn6})
        DataGridView1.Location = New Point(6, 11)
        DataGridView1.Name = "DataGridView1"
        DataGridView1.RowTemplate.Height = 25
        DataGridView1.Size = New Size(780, 400)
        DataGridView1.TabIndex = 2
        ' 
        ' DataGridViewTextBoxColumn3
        ' 
        DataGridViewTextBoxColumn3.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn3.HeaderText = "Item"
        DataGridViewTextBoxColumn3.Name = "DataGridViewTextBoxColumn3"
        ' 
        ' DataGridViewTextBoxColumn7
        ' 
        DataGridViewTextBoxColumn7.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn7.HeaderText = "Expiration Date"
        DataGridViewTextBoxColumn7.Name = "DataGridViewTextBoxColumn7"
        ' 
        ' DataGridViewTextBoxColumn8
        ' 
        DataGridViewTextBoxColumn8.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn8.HeaderText = "Quantity"
        DataGridViewTextBoxColumn8.Name = "DataGridViewTextBoxColumn8"
        ' 
        ' DataGridViewImageColumn5
        ' 
        DataGridViewImageColumn5.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        DataGridViewImageColumn5.HeaderText = ""
        DataGridViewImageColumn5.Image = My.Resources.Resources.draw
        DataGridViewImageColumn5.ImageLayout = DataGridViewImageCellLayout.Zoom
        DataGridViewImageColumn5.Name = "DataGridViewImageColumn5"
        DataGridViewImageColumn5.Resizable = DataGridViewTriState.True
        DataGridViewImageColumn5.SortMode = DataGridViewColumnSortMode.Automatic
        DataGridViewImageColumn5.Width = 50
        ' 
        ' DataGridViewImageColumn6
        ' 
        DataGridViewImageColumn6.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        DataGridViewImageColumn6.HeaderText = ""
        DataGridViewImageColumn6.Image = My.Resources.Resources.trash
        DataGridViewImageColumn6.ImageLayout = DataGridViewImageCellLayout.Zoom
        DataGridViewImageColumn6.Name = "DataGridViewImageColumn6"
        DataGridViewImageColumn6.Resizable = DataGridViewTriState.True
        DataGridViewImageColumn6.SortMode = DataGridViewColumnSortMode.Automatic
        DataGridViewImageColumn6.Width = 50
        ' 
        ' NotificationsPage
        ' 
        NotificationsPage.Controls.Add(NotificationDataGrid)
        NotificationsPage.Location = New Point(4, 24)
        NotificationsPage.Name = "NotificationsPage"
        NotificationsPage.Padding = New Padding(3)
        NotificationsPage.Size = New Size(792, 422)
        NotificationsPage.TabIndex = 7
        NotificationsPage.Text = "Notifications "
        NotificationsPage.UseVisualStyleBackColor = True
        ' 
        ' NotificationDataGrid
        ' 
        NotificationDataGrid.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        NotificationDataGrid.Columns.AddRange(New DataGridViewColumn() {DataGridViewTextBoxColumn9, DataGridViewTextBoxColumn10})
        NotificationDataGrid.Location = New Point(6, 11)
        NotificationDataGrid.Name = "NotificationDataGrid"
        NotificationDataGrid.RowTemplate.Height = 25
        NotificationDataGrid.Size = New Size(780, 400)
        NotificationDataGrid.TabIndex = 2
        ' 
        ' HelpPage
        ' 
        HelpPage.Location = New Point(4, 24)
        HelpPage.Name = "HelpPage"
        HelpPage.Padding = New Padding(3)
        HelpPage.Size = New Size(792, 422)
        HelpPage.TabIndex = 8
        HelpPage.Text = "Help"
        HelpPage.UseVisualStyleBackColor = True
        ' 
        ' DataGridViewTextBoxColumn4
        ' 
        DataGridViewTextBoxColumn4.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn4.HeaderText = "Recipe"
        DataGridViewTextBoxColumn4.Name = "DataGridViewTextBoxColumn4"
        ' 
        ' DataGridViewTextBoxColumn6
        ' 
        DataGridViewTextBoxColumn6.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn6.HeaderText = "Estimated Time"
        DataGridViewTextBoxColumn6.Name = "DataGridViewTextBoxColumn6"
        ' 
        ' DataGridViewImageColumn3
        ' 
        DataGridViewImageColumn3.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        DataGridViewImageColumn3.HeaderText = ""
        DataGridViewImageColumn3.Image = My.Resources.Resources.draw
        DataGridViewImageColumn3.ImageLayout = DataGridViewImageCellLayout.Zoom
        DataGridViewImageColumn3.Name = "DataGridViewImageColumn3"
        DataGridViewImageColumn3.Resizable = DataGridViewTriState.True
        DataGridViewImageColumn3.SortMode = DataGridViewColumnSortMode.Automatic
        DataGridViewImageColumn3.Width = 50
        ' 
        ' DataGridViewImageColumn4
        ' 
        DataGridViewImageColumn4.AutoSizeMode = DataGridViewAutoSizeColumnMode.None
        DataGridViewImageColumn4.HeaderText = ""
        DataGridViewImageColumn4.Image = My.Resources.Resources.trash
        DataGridViewImageColumn4.ImageLayout = DataGridViewImageCellLayout.Zoom
        DataGridViewImageColumn4.Name = "DataGridViewImageColumn4"
        DataGridViewImageColumn4.Resizable = DataGridViewTriState.True
        DataGridViewImageColumn4.SortMode = DataGridViewColumnSortMode.Automatic
        DataGridViewImageColumn4.Width = 50
        ' 
        ' DataGridViewTextBoxColumn9
        ' 
        DataGridViewTextBoxColumn9.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn9.HeaderText = "Alert"
        DataGridViewTextBoxColumn9.Name = "DataGridViewTextBoxColumn9"
        ' 
        ' DataGridViewTextBoxColumn10
        ' 
        DataGridViewTextBoxColumn10.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill
        DataGridViewTextBoxColumn10.HeaderText = "Time"
        DataGridViewTextBoxColumn10.Name = "DataGridViewTextBoxColumn10"
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(800, 450)
        Controls.Add(TabControl)
        Name = "Form1"
        Text = "OurMeals"
        TabControl.ResumeLayout(False)
        HomePage.ResumeLayout(False)
        RecipeGroup.ResumeLayout(False)
        RecipeGroup.PerformLayout()
        CType(HomeDataGridRecipes, ComponentModel.ISupportInitialize).EndInit()
        ListGroup.ResumeLayout(False)
        ListGroup.PerformLayout()
        CType(HomeDataGridLists, ComponentModel.ISupportInitialize).EndInit()
        NotificationGroup.ResumeLayout(False)
        NotificationGroup.PerformLayout()
        CType(HomeDataGridNotification, ComponentModel.ISupportInitialize).EndInit()
        InventoryGroup.ResumeLayout(False)
        InventoryGroup.PerformLayout()
        CType(HomeDataGridInventory, ComponentModel.ISupportInitialize).EndInit()
        InventoryPage.ResumeLayout(False)
        CType(InventoryDataGrid, ComponentModel.ISupportInitialize).EndInit()
        ListPage.ResumeLayout(False)
        CType(ListDataGrid, ComponentModel.ISupportInitialize).EndInit()
        RecipePage.ResumeLayout(False)
        CType(RecipesDataGrid, ComponentModel.ISupportInitialize).EndInit()
        ShoppersPage.ResumeLayout(False)
        CType(DataGridView1, ComponentModel.ISupportInitialize).EndInit()
        NotificationsPage.ResumeLayout(False)
        CType(NotificationDataGrid, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
    End Sub

    Friend WithEvents TabControl As TabControl
    Friend WithEvents HomePage As TabPage
    Friend WithEvents InventoryPage As TabPage
    Friend WithEvents ListPage As TabPage
    Friend WithEvents RecipePage As TabPage
    Friend WithEvents ShoppersPage As TabPage
    Friend WithEvents NotificationsPage As TabPage
    Friend WithEvents HelpPage As TabPage
    Friend WithEvents RecipeGroup As GroupBox
    Friend WithEvents ListGroup As GroupBox
    Friend WithEvents NotificationGroup As GroupBox
    Friend WithEvents InventoryGroup As GroupBox
    Friend WithEvents InventoryLabel As Label
    Friend WithEvents RecipeLabel As Label
    Friend WithEvents ListLabel As Label
    Friend WithEvents NotificationLabel As Label
    Friend WithEvents InventoryDataGrid As DataGridView
    Friend WithEvents Item As DataGridViewTextBoxColumn
    Friend WithEvents ExpirationDate As DataGridViewTextBoxColumn
    Friend WithEvents Quantity As DataGridViewTextBoxColumn
    Friend WithEvents Edit As DataGridViewImageColumn
    Friend WithEvents Delete As DataGridViewImageColumn
    Friend WithEvents HomeDataGridRecipes As DataGridView
    Friend WithEvents HomeDataGridLists As DataGridView
    Friend WithEvents HomeDataGridNotification As DataGridView
    Friend WithEvents HomeDataGridInventory As DataGridView
    Friend WithEvents HomeAlert As DataGridViewTextBoxColumn
    Friend WithEvents HomeTime As DataGridViewTextBoxColumn
    Friend WithEvents HomeItem As DataGridViewTextBoxColumn
    Friend WithEvents HomeExpire As DataGridViewTextBoxColumn
    Friend WithEvents HomeQuantity As DataGridViewTextBoxColumn
    Friend WithEvents HomeRecipeLists As DataGridViewTextBoxColumn
    Friend WithEvents HomeListNames As DataGridViewTextBoxColumn
    Friend WithEvents ListDataGrid As DataGridView
    Friend WithEvents RecipesDataGrid As DataGridView
    Friend WithEvents DataGridViewTextBoxColumn1 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewTextBoxColumn2 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewImageColumn1 As DataGridViewImageColumn
    Friend WithEvents DataGridViewImageColumn2 As DataGridViewImageColumn
    Friend WithEvents DataGridView1 As DataGridView
    Friend WithEvents DataGridViewTextBoxColumn3 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewTextBoxColumn7 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewTextBoxColumn8 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewImageColumn5 As DataGridViewImageColumn
    Friend WithEvents DataGridViewImageColumn6 As DataGridViewImageColumn
    Friend WithEvents NotificationDataGrid As DataGridView
    Friend WithEvents DataGridViewTextBoxColumn4 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewTextBoxColumn6 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewImageColumn3 As DataGridViewImageColumn
    Friend WithEvents DataGridViewImageColumn4 As DataGridViewImageColumn
    Friend WithEvents DataGridViewTextBoxColumn9 As DataGridViewTextBoxColumn
    Friend WithEvents DataGridViewTextBoxColumn10 As DataGridViewTextBoxColumn
End Class
