require 'rubygems'
require 'couchbase'
    class Couchbase::Bucket
      alias old_initialize initialize
      def initialize(*args)
        options = args.last
        if options.is_a?(Hash) && options[:environment]
          self.class.send(:define_method, :environment) do
            return options[:environment]
          end
        end
        old_initialize(*args)
      end
    end
    client = Couchbase::Bucket.new(:environment => :development)
    puts client.design_docs.inspect
ddoc = client.design_docs["dev_catalog"] 
print ddoc.views 
view=ddoc.catalog_view
enum = view.each   
enum.map{|doc| 
puts doc.value 
puts doc.meta 
puts doc.key 
puts doc.id 
puts doc.doc
puts doc.data 
}
